package com.dock.desafio.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
/**
 * @author luiz henrique
 *
 */
class DepositoTest {

	@Test
	void testToString() {
		Deposito deposito = new Deposito();
		deposito.setIdConta(1L);
		deposito.setValor(new BigDecimal(100.0));

		assertEquals(1L, deposito.getIdConta());
		assertEquals(new BigDecimal(100.0), deposito.getValor());
		assertTrue(deposito.toString().contains("Deposito ["));
	}

}
