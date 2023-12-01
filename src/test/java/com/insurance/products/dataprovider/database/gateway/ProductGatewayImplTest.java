package com.insurance.products.dataprovider.database.gateway;

import static com.insurance.products.utils.BuildTestUtils.buildProductDomainWithAnotherBaseAmountAndWithouId;
import static com.insurance.products.utils.BuildTestUtils.buildProductEntity;
import static com.insurance.products.utils.BuildTestUtils.buildProductEntityWithAnotherBaseAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.insurance.products.dataprovider.database.repository.ProductRepository;
import com.insurance.products.utils.BuildTestUtils;

@ExtendWith(MockitoExtension.class)
class ProductGatewayImplTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductGatewayImpl productGatewayImpl;
	
	@Test
	@DisplayName("Should update product when product is already persisted")
	void givenAProductInDatabaseShouldUpdateThisProduct() {
		var productFromDatabase = buildProductEntity();
		when(productRepository.findByNameAndCategory(anyString(), anyString())).thenReturn(Optional.of(productFromDatabase));
		when(productRepository.save(any())).thenReturn(buildProductEntityWithAnotherBaseAmount());
		
		var productUpdated = productGatewayImpl.upsert(buildProductDomainWithAnotherBaseAmountAndWithouId());
		
		assertEquals(productFromDatabase.getId(), productUpdated.id());
		assertNotEquals(productFromDatabase.getBaseAmount(), productUpdated.baseAmount());
		assertNotEquals(productFromDatabase.getAmountWithTax(), productUpdated.amountWithTax());
	}
	
	@Test
	@DisplayName("Given a product not in database should insert this")
	void givenAProductNotInDatabaseShouldInsert() {
		when(productRepository.findByNameAndCategory(anyString(), anyString())).thenReturn(Optional.empty());
		when(productRepository.save(any())).thenReturn(buildProductEntity());
		
		var newProduct = productGatewayImpl.upsert(BuildTestUtils.buildProductDomainWithoudIdAndAmountWithTax());
		
		verify(productRepository).save(any());
		assertNotNull(newProduct.id());
		assertNotNull(newProduct.amountWithTax());
	}
}