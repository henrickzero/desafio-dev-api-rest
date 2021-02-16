package com.dock.desafio.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author luiz henrique
 * Entidade JPA
 *
 */
@Entity
@Table(name = "transacoes")
public class Transacao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_TRANSACAO", nullable = false)
	private Long idTransacao;
//	private Conta conta; Usei LONG, porém poderia ter usado mapeamento JPA ONE TO ONE
	@Column(name = "ID_CONTA", nullable = false)
	private Long idConta;
	@Column(name = "VL_VALOR", nullable = false)
	private BigDecimal valor;
	@Column(name = "DT_TRANSACAO", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.DATE)
	private Date dataTransacao;
	@Column(name = "IC_TIPO", nullable = false)
	@ApiModelProperty(notes = "0=DEPOSITO, 1=SAQUE")	
	@NotNull
	@Min(0)
	@Max(2)
	private Integer tipoTransacao;	
	
	public Long getIdTransacao() {
		return idTransacao;
	}
	public void setIdTransacao(Long idTransacao) {
		this.idTransacao = idTransacao;
	}
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
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Integer getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(Integer tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	@Override
	public String toString() {
		return "Transacao [idTransacao=" + idTransacao + ", idConta=" + idConta + ", valor=" + valor
				+ ", dataTransacao=" + dataTransacao + ", tipoTransacao=" + tipoTransacao + "]";
	}


	

}
