package com.dock.desafio.rules;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dock.desafio.dao.PessoaDao;
import com.dock.desafio.entity.Pessoa;
import com.dock.desafio.util.NotFoundException;
import com.dock.desafio.util.RulesException;
import com.dock.desafio.util.ValidateException;

/**
 * @author luiz henrique
 * Classe de regras de negocio
 *
 */
@Service
public class PessoaRules extends Rules {

	Logger logger = LoggerFactory.getLogger(PessoaRules.class);

	@Autowired
	private PessoaDao pessoaDao;

	/**
	 * @param pessoa
	 * Cria uma pessoa
	 */
	public void criar(Pessoa pessoa) {
		logger.info(pessoa.toString());
		Pessoa p = buscarPorCpf(pessoa.getCpf());
		if (p != null) {
			throw new RulesException("Pessoa já cadastrada");
		}
		List<String> erros = valid(pessoa);
		if (erros.size() > 0) {
			throw new ValidateException(erros.get(0));
		}
		pessoa.setIdPessoa(null);

		pessoaDao.save(pessoa);
	}

	/**
	 * @param pessoa
	 * atualiza uma pessoa
	 */
	public void atualizar(Pessoa pessoa) {	
		logger.info(pessoa.toString());
		Pessoa pessoaEnontrada = pessoaDao.findById(pessoa.getIdPessoa()).get();
		pessoaEnontrada.setNome(pessoa.getNome());
		pessoaEnontrada.setDataNascimento(pessoa.getDataNascimento());		
		pessoaDao.save(pessoaEnontrada);
	}

	/**
	 * @param idPessoa
	 * Deleta umapessoa
	 */
	public void deletar(Long idPessoa) {
		pessoaDao.deleteById(idPessoa);
		logger.info("DELETAR: " + idPessoa);

	}

	/**
	 * @param idPessoa
	 * @return
	 * @throws Exception
	 * recupera uma pessoa
	 */
	public Pessoa get(Long idPessoa) throws Exception {
		logger.info("GET: " + idPessoa);
		try {
			return pessoaDao.findById(idPessoa).get();
		} catch (java.util.NoSuchElementException e) {
			throw new NotFoundException();
		} catch (Exception e) {
			throw new Exception();
		}
	}

	/**
	 * @param cpf
	 * @return
	 * @throws Exception
	 * Busca uma pessoa pelo CPF
	 */
	public Pessoa busca(String cpf) throws Exception {
		Pessoa p = buscarPorCpf(cpf);
		if (p == null) {
			throw new NotFoundException();
		} else {
			return p;
		}
	}

	/**
	 * @param cpf
	 * @return
	 * Busca uma pessoa pelo CPF
	 */
	private Pessoa buscarPorCpf(String cpf) {
		logger.info("BUSCAR: " + cpf);
		return pessoaDao.busca(cpf);
	}

	/**
	 * @return
	 * @throws Exception
	 * Lista todas as pessoas da Base
	 */
	public List<Pessoa> lista() throws Exception {
		return pessoaDao.lista();
	}

}
