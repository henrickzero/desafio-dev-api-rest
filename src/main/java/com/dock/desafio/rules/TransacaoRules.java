package com.dock.desafio.rules;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dock.desafio.dao.TransacaoDao;
import com.dock.desafio.entity.Transacao;
import com.dock.desafio.util.ValidateException;

/**
 * @author luiz henrique
 * Classe de regras de negocio
 *
 */
@Service
public class TransacaoRules extends Rules {
	Logger logger = LoggerFactory.getLogger(TransacaoRules.class);

	@Autowired
	private TransacaoDao transacaoDao;

	public void criar(Transacao transacao) {
		logger.info(transacao.toString());
		List<String> erros = valid(transacao);
		if (erros.size() > 0) {
			throw new ValidateException(erros.get(0));
		}
		transacao.setDataTransacao(new Date());
		transacaoDao.save(transacao);
	}
	
	public List<Transacao> extrato(Long idConta){
		logger.info("extrato: "+idConta);
		return transacaoDao.extrato(idConta);
	}
	
	public List<Transacao> extratoPeriodo(Long idConta, String de, String ate) throws ParseException{
		logger.info("extratoPeriodo: "+idConta+", de: "+de+",ate: "+ate);
		Date dataDe =new SimpleDateFormat("dd-MM-yyyy").parse(de);
		Date dataAte =new SimpleDateFormat("dd-MM-yyyy").parse(ate);		
		return transacaoDao.extratoPeriodo(idConta, dataDe,dataAte);
	}
	
	public List<Transacao> extratoPorData(Long idConta, String data) throws ParseException{
		logger.info("extratoPorData: "+idConta+", data: "+data);
		Date novaData =new SimpleDateFormat("dd-MM-yyyy").parse(data);
		return transacaoDao.extratoPorData(idConta, novaData);
	}
	
	public BigDecimal utilizacaoDiaria(Long idConta, Date data) {
		return transacaoDao.utilizacaoDiaria(idConta, data);
	}

}
