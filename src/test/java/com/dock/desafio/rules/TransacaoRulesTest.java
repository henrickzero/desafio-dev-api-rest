package com.dock.desafio.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dock.desafio.entity.Conta;
import com.dock.desafio.entity.Pessoa;
import com.dock.desafio.entity.Transacao;
/**
 * @author luiz henrique
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class TransacaoRulesTest {
	
	@SpyBean
	private PessoaRules pessoaRules;

	@SpyBean
	private ContaRules contaRules;

	@SpyBean
	private TransacaoRules transacaoRules;

	
	@Test
	void testCriar() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());
		contaRules.criar(conta);
		List<Conta> listaConta = contaRules.lista();
		
		Transacao trsansacao = novaTransacao(listaConta.get(0).getIdConta());
		transacaoRules.criar(trsansacao);
		
		List<Transacao> listaTransacao = transacaoRules.extrato(listaConta.get(0).getIdConta());
		Integer countTransacao = listaTransacao.size();

		assertEquals(1, countTransacao);
		
				
	}

	@Test
	void testExtrato() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());
		contaRules.criar(conta);
		List<Conta> listaConta = contaRules.lista();
		
		Transacao trsansacao = novaTransacao(listaConta.get(0).getIdConta());
		transacaoRules.criar(trsansacao);
		
		List<Transacao> listaTransacao = transacaoRules.extrato(listaConta.get(0).getIdConta());
		Integer countTransacao = listaTransacao.size();

		assertEquals(1, countTransacao);
		
	}

	@Test
	void testExtratoPeriodo() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());
		contaRules.criar(conta);
		List<Conta> listaConta = contaRules.lista();
		
		Transacao trsansacao = novaTransacao(listaConta.get(0).getIdConta());
		transacaoRules.criar(trsansacao);
		

		DateFormat dateFormat =  new  SimpleDateFormat ( "dd-MM-yyyy" );  
		String strDate = dateFormat.format (new Date());
		
		List<Transacao> listaTransacao = transacaoRules.extratoPorData(listaConta.get(0).getIdConta(),strDate);
		Integer countTransacao = listaTransacao.size();

		assertEquals(1, countTransacao);
		
	}

	@Test
	void testExtratoPorData() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());
		contaRules.criar(conta);
		List<Conta> listaConta = contaRules.lista();
		
		Transacao trsansacao = novaTransacao(listaConta.get(0).getIdConta());
		transacaoRules.criar(trsansacao);
		

		DateFormat dateFormat =  new  SimpleDateFormat ( "dd-MM-yyyy" );  
		
		String de = dateFormat.format (new Date());
		String para = dateFormat.format (new Date());
		
		List<Transacao> listaTransacao = transacaoRules.extratoPeriodo(listaConta.get(0).getIdConta(),de,para);
		Integer countTransacao = listaTransacao.size();

		assertEquals(1, countTransacao);
	}
	
	private Pessoa novaPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoa.setDataNascimento(new Date(12333334));
		pessoa.setCpf("22222222222");
		return pessoa;
	}

	private Conta novaConta(Long idPessoa) {
		Conta conta = new Conta();
		conta.setIdPessoa(idPessoa);
		conta.setDataCriacao(new Date());
		conta.setFlagAtivo(true);
		conta.setLimiteSaqueDiario(new BigDecimal(100.0));
		conta.setSaldo(new BigDecimal(0.0));
		conta.setTipoConta(0);
		return conta;
	}
	
	private Transacao novaTransacao(Long idConta) {
		Transacao transacao = new Transacao();
		transacao.setIdConta(idConta);
		transacao.setValor(new BigDecimal(10.0));
		transacao.setTipoTransacao(0);
		return transacao;
	}

}
