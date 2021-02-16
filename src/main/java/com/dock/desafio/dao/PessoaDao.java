package com.dock.desafio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dock.desafio.entity.Pessoa;

/**
 * @author luiz henrique
 *
 */
public interface PessoaDao extends CrudRepository<Pessoa, Long> {
	/**
	 * @param cpf
	 * @return
	 * Recupera uma pessoa pelo CPF
	 */
	@Query(value = "SELECT p FROM Pessoa p WHERE p.cpf=:cpf")
	public Pessoa busca(String cpf);
	
	/**
	 * @return
	 * Lista todas as pessoas da base
	 */
	@Query(value = "SELECT p FROM Pessoa p ORDER BY p.nome ASC")
	public List<Pessoa> lista();

}
