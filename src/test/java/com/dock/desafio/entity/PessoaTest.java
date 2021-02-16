package com.dock.desafio.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
/**
 * @author luiz henrique
 *
 */
class PessoaTest {

	@Test
	void testToString() {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(1L);
        pessoa.setNome("nome");
        pessoa.setDataNascimento(new Date(12333334));
        pessoa.setCpf("22222222222");
        
        
        assertEquals(1L, pessoa.getIdPessoa());
        assertEquals("nome", pessoa.getNome());
        assertEquals(new Date(12333334), pessoa.getDataNascimento());
        assertEquals("22222222222", pessoa.getCpf());
        assertTrue(pessoa.toString().contains("Pessoa ["));
	}

}
