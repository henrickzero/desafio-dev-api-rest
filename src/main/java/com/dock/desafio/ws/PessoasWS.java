package com.dock.desafio.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dock.desafio.entity.Pessoa;
import com.dock.desafio.rules.PessoaRules;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author luiz henrique
 *
 */
@Controller	
@RequestMapping(path="/v1/pessoas") 
public class PessoasWS {
	@Autowired
	private PessoaRules pessoaRules;

	
	@ApiOperation(value = "Realiza a criacao de uma pessoa", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao criar pessoa"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PostMapping(path="/criar")
	public @ResponseBody String criar (@RequestBody Pessoa pessoa) {		
		pessoaRules.criar(pessoa);
		return "Sucesso ao Criar Pessoa";
	}
	
	@ApiOperation(value = "Realiza a atualizacao de uma Pessoa", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao Atualizar Pessoa"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PutMapping(path="/atualizar")
	public @ResponseBody String atualizar (@RequestBody Pessoa pessoa) {
		pessoaRules.atualizar(pessoa);
		return "Sucesso ao Atualizar Pessoa";
	}

	@ApiOperation(value = "Recupera uma Pessoa pelo ID", response = Pessoa.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma Pessoa"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 404, message = "Pessoa nao encontrada"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/{idPessoa}")
	public @ResponseBody Pessoa get(@PathVariable("idPessoa") Long idPessoa) throws Exception {
		return pessoaRules.get(idPessoa);
	}
	
	@ApiOperation(value = "Recupera uma Pessoa pelo CPF", response = Pessoa.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma Pessoa"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 404, message = "Pessoa nao encontrada"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/busca/{cpf}")
	public @ResponseBody Pessoa busca(@PathVariable("cpf") String cpf) throws Exception {
		return pessoaRules.busca(cpf);
	}
	
	@ApiOperation(value = "Deleta uma Pessoa pelo ID", response = Pessoa.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Pessoa deletada"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 404, message = "Pessoa nao encontrada"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@DeleteMapping("/{idPessoa}")
	public @ResponseBody String delete(@PathVariable("idPessoa") Long idPessoa) throws Exception {
		pessoaRules.deletar(idPessoa);
		return "Pessoa deletada";
	}
	
	@ApiOperation(value = "Recupera uma lista contendo todas as Pessoas, ordenadas por Nome")
	@ApiResponses(value = {			
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/lista")
	public @ResponseBody List<Pessoa> lista() throws Exception {
		return pessoaRules.lista();
	}
	
}
