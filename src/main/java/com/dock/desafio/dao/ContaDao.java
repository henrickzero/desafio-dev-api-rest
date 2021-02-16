package com.dock.desafio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dock.desafio.entity.Conta;


/**
 * @author luiz henrique
 *
 */
public interface ContaDao extends CrudRepository<Conta, Long> {
	/**
	 * lista todoas as contas da base ordenado por id da conta
	 * @return
	 */
	@Query(value = "SELECT c FROM Conta c ORDER BY c.idConta ASC")
	public List<Conta> lista();

}
