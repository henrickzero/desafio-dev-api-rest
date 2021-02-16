package com.dock.desafio.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * @author luiz henrique
 *
 */
class SaqueTest {

	@Test
	void testToString() {
		Saque saque = new Saque();
		saque.setIdConta(1L);
		saque.setValor(new BigDecimal(100.0));

		assertEquals(1L, saque.getIdConta());
		assertEquals(new BigDecimal(100.0), saque.getValor());
		assertTrue(saque.toString().contains("Saque ["));
	}
}
