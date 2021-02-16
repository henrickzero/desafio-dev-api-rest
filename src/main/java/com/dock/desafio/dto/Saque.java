package com.dock.desafio.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * @author luiz henrique
 * DTO
 *
 */
public class Saque {
	@NotNull
	private Long idConta; 
	@NotNull
	private BigDecimal valor;
	
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Saque [idConta=" + idConta + ", valor=" + valor + "]";
	} 
	
	

}
