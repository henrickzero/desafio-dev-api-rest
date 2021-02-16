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
@Table(name = "contas")
public class Conta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_CONTA", nullable = false)
	@ApiModelProperty(notes = "Nao e obrigatorio na criacao")
	private Long idConta;
//	private Pessoa pessoa; Usei LONG, porém poderia ter usado mapeamento JPA ONE TO ONE
	@NotNull
	@Column(name = "ID_PESSOA", nullable = false)
	private Long idPessoa;
	@NotNull
	@Column(name = "VL_SALDO", nullable = false)
	private BigDecimal saldo;
	@NotNull
	@Column(name = "VL_LIMITE_SAQUE_DIARIO", nullable = false)
	private BigDecimal limiteSaqueDiario;
	@NotNull
	@Column(name = "IS_FLAG_ATIVO", nullable = false)
	private Boolean flagAtivo;
	
	@Column(name = "IC_TIPO_CONTA", nullable = false)
	@ApiModelProperty(notes = "0=CONTA CORRENTE, 1=CONTA POUPANCA, 2=CONTA SALARIO")
	
	@NotNull
	@Min(0)
	@Max(2)
	private Integer tipoConta;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "DT_CRIACAO", nullable = false)
	@ApiModelProperty(notes = "Nao informar na criacao")
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public BigDecimal getLimiteSaqueDiario() {
		return limiteSaqueDiario;
	}
	public void setLimiteSaqueDiario(BigDecimal limiteSaqueDiario) {
		this.limiteSaqueDiario = limiteSaqueDiario;
	}
	public Boolean getFlagAtivo() {
		return flagAtivo;
	}
	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}
	public Integer getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(Integer tipoConta) {
		this.tipoConta = tipoConta;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	@Override
	public String toString() {
		return "Conta [idConta=" + idConta + ", idPessoa=" + idPessoa + ", saldo=" + saldo + ", limiteSaqueDiario="
				+ limiteSaqueDiario + ", flagAtivo=" + flagAtivo + ", tipoConta=" + tipoConta + ", dataCriacao="
				+ dataCriacao + "]";
	}
	

}
