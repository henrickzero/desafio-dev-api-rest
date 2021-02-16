package com.dock.desafio.ws;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.dock.desafio.dto.Deposito;
import com.dock.desafio.dto.Saque;
import com.dock.desafio.entity.Conta;
import com.dock.desafio.entity.Pessoa;
import com.google.gson.Gson;

/**
 * @author luiz henrique * 
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class ContasWSTest {
	
	private Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;


	/**
	 * Testa criar Contas
	 * @throws Exception
	 */
	@Test
	@Order(1)	
	void testCriar() throws Exception {
		
//		Cria uma pessoa
        Pessoa pessoa = novaPessoa();   
        
        JSONObject jsonObject = new JSONObject(gson.toJson(pessoa));
        jsonObject.put("dataNascimento", "12-12-2002");

        mockMvc.perform(post("/v1/pessoas/criar")
                .contentType("application/json")
                .content(jsonObject.toString()))
                .andExpect(status().isOk());    
        
        MvcResult result = mockMvc.perform(get("/v1/pessoas/busca/22222222222")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();        
        jsonObject = new JSONObject(content);        
        String id = jsonObject.getString("idPessoa");
        
//        Cria uma conta
        Conta conta = novaConta(Long.parseLong(id)); 
        jsonObject = new JSONObject(gson.toJson(conta));
        jsonObject.remove("dataCriacao");
        
        mockMvc.perform(post("/v1/contas/criar")
                .contentType("application/json")
                .content(jsonObject.toString()))
                .andExpect(status().isOk());    
        
        
        
	}

	@Test
	@Order(2)
	void testDepositar() throws Exception {
        Deposito deposito = new Deposito();  
        deposito.setIdConta(1L);
        deposito.setValor(new BigDecimal(10.0));
        
        JSONObject jsonObject = new JSONObject(gson.toJson(deposito));
        
        mockMvc.perform(post("/v1/contas/depositar")
                .contentType("application/json")
                .content(jsonObject.toString()))
                .andExpect(status().isOk());  
	}

	@Test
	@Order(3)
	void testSacar() throws Exception {
        Saque saque = new Saque();  
        saque.setIdConta(1L);
        saque.setValor(new BigDecimal(10.0));
        
        JSONObject jsonObject = new JSONObject(gson.toJson(saque));
        
        mockMvc.perform(post("/v1/contas/sacar")
                .contentType("application/json")
                .content(jsonObject.toString()))
                .andExpect(status().isOk());  
	}
	
	@Test
	@Order(3)
	void testSacarLimite() throws Exception {
		Saque saque = new Saque();  
		saque.setIdConta(1L);
		saque.setValor(new BigDecimal(100.0));
		
		JSONObject jsonObject = new JSONObject(gson.toJson(saque));
		
		mockMvc.perform(post("/v1/contas/sacar")
				.contentType("application/json")
				.content(jsonObject.toString()))
		.andExpect(status().isBadRequest());  
	}

	@Test
	@Order(4)
	void testSaldo() throws Exception {
        mockMvc.perform(get("/v1/contas/1/saldo")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());  
	}

	@Test
	void testBloquearConta() throws Exception {
        mockMvc.perform(put("/v1/contas/1/bloquearConta")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());  
	}

	@Test
	void testDesbloquearConta()  throws Exception {
        mockMvc.perform(put("/v1/contas/1/desbloquearConta")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());  
	}

	@Test
	void testExtrato() throws Exception {
        mockMvc.perform(get("/v1/contas/1/extrato")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());  
	}

	@Test
	void testExtratoPeriodo() throws Exception {
		DateFormat dateFormat =  new  SimpleDateFormat ( "dd-MM-yyyy" );  
		
		String de = dateFormat.format (new Date());
		String ate = dateFormat.format (new Date());
		
        mockMvc.perform(get("/v1/contas/1/extratoPorPeriodo?de="+de+"&ate="+ate)
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk()); 
	}
	
	
	@Test
	@Order(100)
	void testDelete()  throws Exception{
        MvcResult result = mockMvc.perform(get("/v1/pessoas/busca/22222222222")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();
        
        JSONObject jsonObject = new JSONObject(content);
        
        String id = jsonObject.getString("idPessoa");
        
        mockMvc.perform(delete("/v1/pessoas/"+id)
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());
	}

	private Pessoa novaPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("nome");
		pessoa.setDataNascimento(new Date(12333334));
		pessoa.setCpf("22222222222");
		return pessoa;
	}

	private Conta novaConta(Long idPessoa) {
		Conta conta = new Conta();
		conta.setIdPessoa(idPessoa);
		conta.setDataCriacao(new Date());
		conta.setFlagAtivo(true);
		conta.setLimiteSaqueDiario(new BigDecimal(100.0));
		conta.setSaldo(new BigDecimal(0.0));
		conta.setTipoConta(0);
		return conta;
	}
	

}
