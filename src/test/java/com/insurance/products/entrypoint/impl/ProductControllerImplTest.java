package com.insurance.products.entrypoint.impl;

import static com.insurance.products.utils.BuildTestUtils.AMOUNT_WITH_TAX;
import static com.insurance.products.utils.BuildTestUtils.buildProductDomainWithIdAndAmountWithTax;
import static com.insurance.products.utils.BuildTestUtils.buildProductRequestDto;
import static com.insurance.products.utils.BuildTestUtils.buildProductToUpsert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insurance.products.core.usecase.UpsertProductUseCase;
import com.insurance.products.entrypoint.controller.impl.ProductControllerImpl;

@ExtendWith(MockitoExtension.class)
class ProductControllerImplTest {
	
	@Mock
	private UpsertProductUseCase upsertProductUseCase;
	
	@InjectMocks
	private ProductControllerImpl productController;
	
	@Test
	@DisplayName("Should upsert a product with success")
	void givenAProductShouldUpsertWithSuccess() {
		var productRequest = buildProductRequestDto();
		when(upsertProductUseCase.execute(buildProductToUpsert())).thenReturn(buildProductDomainWithIdAndAmountWithTax());
		
		var response = productController.upsertProduct(productRequest);
		
		assertEquals(productRequest.name(), response.name());
		assertEquals(productRequest.category().name(), response.category());
		assertEquals(AMOUNT_WITH_TAX, response.amountWithTax());
		assertEquals(productRequest.baseAmount(), response.baseAmount());
		assertNotNull(response.id());
	}
}