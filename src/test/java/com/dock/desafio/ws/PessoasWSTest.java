package com.dock.desafio.ws;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.dock.desafio.entity.Pessoa;
import com.google.gson.Gson;
/**
 * @author luiz henrique
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PessoasWSTest {
	
	private Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;
   
	
	@Test
	@Order(1)
	void testCriar() throws Exception {

        Pessoa pessoa = novaPessoa();   
        
        JSONObject jsonObject = new JSONObject(gson.toJson(pessoa));
        jsonObject.put("dataNascimento", "12-12-2002");

        mockMvc.perform(post("/v1/pessoas/criar")
                .contentType("application/json")
                .content(jsonObject.toString()))
                .andExpect(status().isOk());        
    }
	

	@Test
	@Order(2)
	void testAtualizar() throws Exception {
        Pessoa pessoa = novaPessoa();   
        
        JSONObject jsonObject = new JSONObject(gson.toJson(pessoa));
        jsonObject.put("dataNascimento", "12-12-2002");
        
        MvcResult result = mockMvc.perform(get("/v1/pessoas/busca/22222222222")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();
        
        jsonObject = new JSONObject(content);
        jsonObject.put("nome", "novo");
        

       mockMvc.perform(put("/v1/pessoas/atualizar")
                .contentType("application/json")
                .content(jsonObject.toString()))        
                .andExpect(status().isOk());
        
        

	}

	@Test
	@Order(3)
	void testGet()  throws Exception{
        MvcResult result = mockMvc.perform(get("/v1/pessoas/busca/22222222222")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
        
        String content = result.getResponse().getContentAsString();
        
        JSONObject jsonObject = new JSONObject(content);
        
        String id = jsonObject.getString("idPessoa");
        
        mockMvc.perform(get("/v1/pessoas/"+id)
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk());
        
	}

	@Test
	@Order(4)
	void testBusca()  throws Exception{
        MvcResult result = mockMvc.perform(get("/v1/pessoas/busca/22222222222")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
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

	@Test
	@Order(5)
	void testLista()  throws Exception{
        MvcResult result = mockMvc.perform(get("/v1/pessoas/lista")
                .contentType("application/json")
                .content(""))
                .andExpect(status().isOk())
                .andReturn();
	}

private Pessoa novaPessoa() {
	Pessoa pessoa = new Pessoa();
	pessoa.setNome("nome");
	pessoa.setDataNascimento(new Date(12333334));
	pessoa.setCpf("22222222222");
	return pessoa;
}

}
