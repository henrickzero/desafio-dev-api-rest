package com.dock.desafio.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dock.desafio.entity.Pessoa;
/**
 * @author luiz henrique
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PessoaRulesTest {

	@SpyBean
	private PessoaRules pessoaRules;

	private Pessoa novaPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoa.setDataNascimento(new Date(12333334));
		pessoa.setCpf("22222222222");
		return pessoa;
	}

	@Test
	void testCriar() throws Exception {
		Pessoa pessoa = novaPessoa();

		pessoaRules.criar(pessoa);
		Integer countPessoa = pessoaRules.lista().size();

		assertEquals(1, countPessoa);
	}

	@Test
	void testAtualizar() throws Exception {
		Pessoa pessoa = novaPessoa();

		pessoaRules.criar(pessoa);
		Integer countPessoa = pessoaRules.lista().size();
		assertEquals(1, countPessoa);

		pessoa.setNome("novo");
		pessoaRules.atualizar(pessoa);
		Pessoa pessoaAtualizada = pessoaRules.lista().get(0);
		assertEquals("novo", pessoaAtualizada.getNome());

	}

	@Test
	void testDeletar() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		List<Pessoa> listaPessoas =  pessoaRules.lista();
		Integer countPessoa = listaPessoas.size();
		assertEquals(1, countPessoa);
		
		pessoaRules.deletar(listaPessoas.get(0).getIdPessoa());
		listaPessoas =  pessoaRules.lista();
		assertEquals(0, listaPessoas.size());
	}

	@Test
	void testGet() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		List<Pessoa> listaPessoas =  pessoaRules.lista();
		Integer countPessoa = listaPessoas.size();
		assertEquals(1, countPessoa);
		
		Pessoa pessoaEncontrada = pessoaRules.get(listaPessoas.get(0).getIdPessoa()); 
		
		assertEquals(listaPessoas.get(0).getIdPessoa(), pessoaEncontrada.getIdPessoa());
		
	}

	@Test
	void testBusca() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		List<Pessoa> listaPessoas =  pessoaRules.lista();
		Integer countPessoa = listaPessoas.size();
		assertEquals(1, countPessoa);
		
		
		Pessoa pessoaEncontrada = pessoaRules.busca(listaPessoas.get(0).getCpf()); 
		
		assertEquals(listaPessoas.get(0).getIdPessoa(), pessoaEncontrada.getIdPessoa());
	}

	@Test
	void testLista() throws Exception {
		Pessoa pessoa = novaPessoa();
		pessoaRules.criar(pessoa);
		List<Pessoa> listaPessoas =  pessoaRules.lista();
		Integer countPessoa = listaPessoas.size();
		assertEquals(1, countPessoa);
	}

}
