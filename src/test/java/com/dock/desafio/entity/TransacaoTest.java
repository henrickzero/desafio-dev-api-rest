package com.dock.desafio.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
/**
 * @author luiz henrique
 *
 */
class TransacaoTest {

	@Test
	void testToString() {
        Transacao transacao = new Transacao();
        transacao.setIdTransacao(1L);
        transacao.setIdConta(1L);
        transacao.setValor(new BigDecimal(100.0));
        transacao.setDataTransacao(new Date(12333334));
        
        
        assertEquals(1L, transacao.getIdTransacao());
        assertEquals(1L, transacao.getIdConta());
        assertEquals(new BigDecimal(100.0), transacao.getValor());
        assertEquals(new Date(12333334), transacao.getDataTransacao());
        assertTrue(transacao.toString().contains("Transacao ["));
	}

}
