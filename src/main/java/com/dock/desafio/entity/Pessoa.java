package com.dock.desafio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author luiz henrique
 * Entidade JPA
 *
 */
@Entity
@Table(name = "pessoas")
public class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PESSOA", nullable = false)
	@ApiModelProperty(notes = "Nao e obrigatorio na criacao")
	private Long idPessoa;
	@Column(name = "NM_PESSOA", nullable = false)
	@NotNull
	private String nome;
	@Size(message = "CPF valido, 11 digitos",min = 11,max = 11)
	@NotNull
	@Column(name = "NU_CPF", nullable = false)
	private String cpf;
	@Column(name = "DT_NASCIMENTO", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ "]";
	}
	
	
	
	

}
