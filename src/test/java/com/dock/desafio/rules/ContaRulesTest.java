package com.dock.desafio.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dock.desafio.entity.Conta;
import com.dock.desafio.entity.Pessoa;

/**
 * @author luiz henrique
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class ContaRulesTest {

	@SpyBean
	private PessoaRules pessoaRules;

	@SpyBean
	private ContaRules contaRules;

	@Test
	void testCriar() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());

		contaRules.criar(conta);

		Integer countConta = contaRules.lista().size();

		assertEquals(1, countConta);

	}

	@Test
	void testSaldo() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());

		contaRules.criar(conta);

		List<Conta> listaConta = contaRules.lista();
		Integer countConta = listaConta.size();

		assertEquals(1, countConta);

		BigDecimal saldo = contaRules.saldo(listaConta.get(0).getIdConta());

		assertEquals(new BigDecimal(0.0), saldo);

	}

	@Test
	void testBloquearConta() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());

		contaRules.criar(conta);

		List<Conta> listaConta = contaRules.lista();
		Integer countConta = listaConta.size();

		assertEquals(1, countConta);

		contaRules.bloquearConta(listaConta.get(0).getIdConta());

		listaConta = contaRules.lista();
		countConta = listaConta.size();
		assertEquals(1, countConta);

		assertEquals(false, listaConta.get(0).getFlagAtivo());

	}

	@Test
	void testDesbloquearConta() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		Pessoa pessoaCriada = pessoaRules.busca("22222222222");

		Conta conta = novaConta(pessoaCriada.getIdPessoa());

		contaRules.criar(conta);

		List<Conta> listaConta = contaRules.lista();
		Integer countConta = listaConta.size();

		assertEquals(1, countConta);

		contaRules.desbloquearConta(listaConta.get(0).getIdConta());

		listaConta = contaRules.lista();
		countConta = listaConta.size();
		assertEquals(1, countConta);

		assertEquals(true, listaConta.get(0).getFlagAtivo());

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

}
