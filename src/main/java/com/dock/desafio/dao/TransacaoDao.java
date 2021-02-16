package com.dock.desafio.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dock.desafio.entity.Transacao;



/**
 * @author luiz henrique
 *
 */
public interface TransacaoDao extends CrudRepository<Transacao, Integer> {
	/**
	 * @param idConta
	 * @return
	 */
	@Query(value = "SELECT t FROM Transacao t WHERE t.idConta=:idConta ORDER BY t.dataTransacao DESC")
	public List<Transacao> extrato(Long idConta);
	
	/**
	 * @param idConta
	 * @param dataInicio
	 * @param dataFim
	 * @return
	 */
	@Query(value = "SELECT t FROM Transacao t WHERE t.idConta=:idConta "
			+ " AND t.dataTransacao >= :dataInicio AND t.dataTransacao <= :dataFim"
			+ " ORDER BY t.dataTransacao DESC")
	public List<Transacao> extratoPeriodo(Long idConta, Date dataInicio, Date dataFim);
	
	/**
	 * @param idConta
	 * @param data
	 * @return
	 */
	@Query(value = "SELECT t FROM Transacao t WHERE t.idConta=:idConta "
			+ " AND t.dataTransacao=:data"
			+ " ORDER BY t.dataTransacao DESC")
	public List<Transacao> extratoPorData(Long idConta, Date data);
	
	/**
	 * @param idConta
	 * @param data
	 * @return
	 */
	@Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.tipoTransacao=1 AND t.idConta=:idConta AND t.dataTransacao=:data")
	public BigDecimal utilizacaoDiaria(Long idConta, Date data);

}
