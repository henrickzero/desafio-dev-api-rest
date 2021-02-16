package com.dock.desafio.ws;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dock.desafio.dto.Deposito;
import com.dock.desafio.dto.Saque;
import com.dock.desafio.entity.Conta;
import com.dock.desafio.entity.Transacao;
import com.dock.desafio.rules.ContaRules;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author luiz henrique
 *
 */
@Controller	
@RequestMapping(path="/v1/contas") 
public class ContasWS {
	@Autowired
	private ContaRules contaRules;
	
	@ApiOperation(value = "Realiza a criacao de uma Conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao criar Conta"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PostMapping(path="/criar")
	public @ResponseBody String criar (@RequestBody Conta conta) {		
		contaRules.criar(conta);
		return "Sucesso ao Criar Conta";
	}
	
	@ApiOperation(value = "Realiza operação de depósito em uma conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao realizar Deposito"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PostMapping(path="/depositar")
	public @ResponseBody String depositar (@RequestBody Deposito deposito) {		
		contaRules.depositar(deposito);
		return "Sucesso ao realizar Deposito";
	}
	
	@ApiOperation(value = "Realiza operação de saque em uma conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao realizar Saque"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PostMapping(path="/sacar")
	public @ResponseBody String sacar (@RequestBody Saque saque) {		
		contaRules.sacar(saque);
		return "Sucesso ao realizar Saque";
	}
	
	@ApiOperation(value = "Realiza operação de consulta de saldo em determinada conta", response = BigDecimal.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "BigDecimal Ex: 100.0"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 404, message = "Pessoa nao encontrada"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/{idConta}/saldo")
	public @ResponseBody BigDecimal saldo(@PathVariable("idConta") Long idConta) throws Exception {
		return contaRules.saldo(idConta);
	}
	
	@ApiOperation(value = "Realiza o bloqueio de uma conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao bloquear Conta"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PutMapping(path="/{idConta}/bloquearConta")
	public @ResponseBody String bloquearConta (@PathVariable("idConta") Long idConta) {		
		contaRules.bloquearConta(idConta);
		return "Sucesso ao bloquear Conta";
	}
	
	@ApiOperation(value = "Realiza o desbloquei de uma conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Sucesso ao desbloquear Conta"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@PutMapping(path="/{idConta}/desbloquearConta")
	public @ResponseBody String desbloquearConta (@PathVariable("idConta") Long idConta) {		
		contaRules.desbloquearConta(idConta);
		return "Sucesso ao bloquear Conta";
	}

	
	
	@ApiOperation(value = "Recupera o extrato de transações de uma conta", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Transacao[]"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/{idConta}/extrato")
	public @ResponseBody List<Transacao> extrato(@PathVariable("idConta") Long idConta) throws Exception {
		return contaRules.extrato(idConta, null, null, null);
	}
	
	@ApiOperation(value = "Recupera o extrato de transações de uma conta por periodo", response = String.class)
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Transacao[]"),
	    @ApiResponse(code = 400, message = "Regra de negocio"),
	    @ApiResponse(code = 401, message = "Acesso nao autorizado"),
	    @ApiResponse(code = 403, message = "Voce nao possui permissao para acessar o recurso"),
	    @ApiResponse(code = 500, message = "Erro interno, por favor tente mais tarde")
	})
	@GetMapping("/{idConta}/extratoPorPeriodo")
	public @ResponseBody List<Transacao> extratoPeriodo(@PathVariable("idConta") Long idConta, @RequestParam(name = "data", required = false) String data,@RequestParam(name = "de", required = false) String de,@RequestParam(name = "ate", required = false) String ate) throws Exception {
		return contaRules.extrato(idConta, data, de, ate);
	}
	
}
