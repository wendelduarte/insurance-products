package com.insurance.products.entrypoint.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.insurance.products.dataprovider.database.repository.ProductRepository;
import com.insurance.products.utils.BuildTestUtils;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ProductRepository productRepository;

	@Test
	@DisplayName("Given a new product should save this product.")
	public void givenANewProductShouldSaveThisProduct() throws Exception {
		var body = BuildTestUtils.getInsuranceAutoRequest();
		
		MockHttpServletRequestBuilder request = post("/products").contentType(MediaType.APPLICATION_JSON).content(body)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.nome", is("Seguro Auto Individual")))
				.andExpect(jsonPath("$.categoria", is("AUTO")))
				.andExpect(jsonPath("$.preco_base", is(50.00)))
				.andExpect(jsonPath("$.preco_tarifado", is(55.25)));
	}
	
	@Test
	@DisplayName("Given a product already in database, just update product.")
	public void givenAProductAlreadyInDatabaseThenUpdateProduct() throws Exception {
		var productEntity = BuildTestUtils.buildProductEntity();
		var savedProduct = productRepository.save(productEntity);
		
		
		var body = BuildTestUtils.getInsuranceVidaRequest();
		
		MockHttpServletRequestBuilder request = post("/products").contentType(MediaType.APPLICATION_JSON).content(body)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(savedProduct.getId())))
				.andExpect(jsonPath("$.nome", is("Seguro de Vida Individual")))
				.andExpect(jsonPath("$.categoria", is("VIDA")))
				.andExpect(jsonPath("$.preco_base", is(100.00)))
				.andExpect(jsonPath("$.preco_tarifado", is(103.20)));
	}
	
}