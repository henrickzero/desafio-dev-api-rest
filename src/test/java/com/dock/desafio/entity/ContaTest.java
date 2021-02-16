package com.dock.desafio.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
/**
 * @author luiz henrique
 *
 */
class ContaTest {

	@Test
	void testToString() {
        Conta conta = new Conta();
        conta.setIdConta(1L);
        conta.setIdPessoa(1L);
        conta.setFlagAtivo(true);
        conta.setDataCriacao(new Date(12333334));
        conta.setLimiteSaqueDiario(new BigDecimal(100.0));
        conta.setSaldo(new BigDecimal(100.0));
        
        assertEquals(1L, conta.getIdConta());
        assertEquals(1L, conta.getIdPessoa());
        assertEquals(true, conta.getFlagAtivo());
        assertEquals(new Date(12333334), conta.getDataCriacao());
        assertEquals(new BigDecimal(100.0), conta.getLimiteSaqueDiario());
        assertEquals(new BigDecimal(100.0), conta.getSaldo());
        assertTrue(conta.toString().contains("Conta ["));
	}

}
