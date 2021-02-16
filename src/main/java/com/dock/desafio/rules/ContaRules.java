package com.dock.desafio.rules;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dock.desafio.dao.ContaDao;
import com.dock.desafio.dto.Deposito;
import com.dock.desafio.dto.Saque;
import com.dock.desafio.entity.Conta;
import com.dock.desafio.entity.Pessoa;
import com.dock.desafio.entity.Transacao;
import com.dock.desafio.util.NotFoundException;
import com.dock.desafio.util.RulesException;
import com.dock.desafio.util.ValidateException;

/**
 * @author luiz henrique
 * Classe de regras de negocio
 *
 */
@Service
public class ContaRules extends Rules {
	Logger logger = LoggerFactory.getLogger(ContaRules.class);

	@Autowired
	private ContaDao contaDao;
	
	@Autowired(required = false)
	private TransacaoRules transacaoRules;


	/**
	 * @param conta
	 * Cria uma conta
	 */
	public void criar(Conta conta) {
		logger.info(conta.toString());
		List<String> erros = valid(conta);		
		if(erros.size()>0) {
			throw new ValidateException(erros.get(0));
		}
		conta.setIdConta(null);		
		conta.setDataCriacao(new Date());		
		contaDao.save(conta);		
	}
	
	/**
	 * @param idConta
	 * @return
	 * @throws Exception
	 * recupera uma conta
	 */
	public Conta get(Long idConta) throws Exception {
		logger.info("GET: " + idConta);
		try {
			return contaDao.findById(idConta).get();
		} catch (java.util.NoSuchElementException e) {
			throw new NotFoundException();
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	/**
	 * @param deposito
	 * Realiza um deposito, alterar o saldo e cria uma transacao
	 */
	@Transactional
	public void depositar(Deposito deposito) {
		logger.info(deposito.toString());
		Conta conta = contaDao.findById(deposito.getIdConta()).get();
		if(Boolean.FALSE.equals(conta.getFlagAtivo())) {
			throw new RulesException("Conta bloqueada");
		}
		Transacao transacao = new Transacao();
		transacao.setIdConta(conta.getIdConta());
		transacao.setValor(deposito.getValor());
		transacao.setTipoTransacao(0);
		transacaoRules.criar(transacao);
		conta.setSaldo(conta.getSaldo().add(deposito.getValor()));
		contaDao.save(conta);
	}
	
	/**
	 * @param deposito
	 * Realiza um saque, alterar o saldo e cria uma transacao
	 */
	@Transactional
	public void sacar(Saque saque) {
		logger.info(saque.toString());
		Conta conta = contaDao.findById(saque.getIdConta()).get();
		
		if(Boolean.FALSE.equals(conta.getFlagAtivo())) {
			throw new RulesException("Conta bloqueada");
		}
		
		BigDecimal limite = transacaoRules.utilizacaoDiaria(conta.getIdConta(), new Date());
		
		if(limite==null) {
			limite = new BigDecimal(0.0);
		}
		limite = limite.add(saque.getValor());
		
		if(!(limite.doubleValue()<=conta.getLimiteSaqueDiario().doubleValue())) {
			throw new RulesException("Limite diario excedido");
		}
		
		BigDecimal novoValor = conta.getSaldo().subtract(saque.getValor());
		if(novoValor.doubleValue()<0.0) {
			throw new RulesException("Saldo insuficiente");
		}
		
		Transacao transacao = new Transacao();
		transacao.setIdConta(conta.getIdConta());
		transacao.setValor(saque.getValor());
		transacao.setTipoTransacao(1);
		transacaoRules.criar(transacao);
		conta.setSaldo(conta.getSaldo().subtract(saque.getValor()));
		contaDao.save(conta);
	}
	
	/**
	 * @param idConta
	 * @return
	 * Lista o saldo da conta
	 */
	public BigDecimal saldo(Long idConta) {
		logger.info("saldo: "+idConta);
		Conta conta = contaDao.findById(idConta).get();
		return conta.getSaldo();		
	}
	
	/**
	 * @param idConta
	 * Bloqueia uma conta
	 */
	@Transactional
	public void bloquearConta(Long idConta) {
		logger.info("Bloquaer: "+idConta);
		Conta conta = contaDao.findById(idConta).get();
		conta.setFlagAtivo(false);
		contaDao.save(conta);
	}
	
	/**
	 * @param idConta
	 * Desbloqueia uma conta
	 */
	@Transactional
	public void desbloquearConta(Long idConta) {
		logger.info("Bloquaer: "+idConta);
		Conta conta = contaDao.findById(idConta).get();
		conta.setFlagAtivo(true);
		contaDao.save(conta);
	}
	

	/**
	 * @param idConta
	 * @param data
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 * @throws ParseException
	 * Recupera extrato de transacoes da conta
	 */
	public List<Transacao> extrato(Long idConta, String data, String dataInicio, String dataFim) throws ParseException{
		if(data != null) {
			return transacaoRules.extratoPorData(idConta, data);
		}else if(dataInicio != null || dataFim != null) {
			return transacaoRules.extratoPeriodo(idConta, dataInicio, dataFim);
		}else {
			return transacaoRules.extrato(idConta);
		}
	}
	

	public List<Conta> lista() throws Exception {
		return contaDao.lista();
	}
}
