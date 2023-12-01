package com.insurance.products.core.usecase.impl;

import static com.insurance.products.utils.BuildTestUtils.buildProductDomainWithIdAndAmountWithTax;
import static com.insurance.products.utils.BuildTestUtils.buildProductDomainWithoudIdAndAmountWithTax;
import static com.insurance.products.utils.BuildTestUtils.buildProductToUpsert;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insurance.products.core.gateway.database.ProductGateway;

@ExtendWith(MockitoExtension.class)
class UpsertProductsUseCaseImplTest {
	
	@Mock
	private ProductGateway productGateway;
	
	@InjectMocks
	private UpsertProductUseCaseImpl upsertProductUseCaseImpl;

	@Test
	@DisplayName("Given a product to upsert should calculate base amount with tax and upsert product with success")
	void givenAProductToUpsertShouldExceuteWithSuccess() {
		when(productGateway.upsert(eq(buildProductDomainWithoudIdAndAmountWithTax()))).thenReturn(buildProductDomainWithIdAndAmountWithTax());
		
		var productDomain = upsertProductUseCaseImpl.execute(buildProductToUpsert());
		
		assertNotNull(productDomain.id());
		assertNotNull(productDomain.amountWithTax());
	}
}