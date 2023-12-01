package com.insurance.products.utils;

import static com.insurance.products.core.domain.enums.Category.VIDA;

import java.math.BigDecimal;
import java.util.UUID;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.domain.dto.ProductToUpsert;
import com.insurance.products.dataprovider.database.entities.ProductEntity;
import com.insurance.products.entrypoint.dto.request.ProductRequestDto;

public class BuildTestUtils {
	
	public static final String PRODUCT_NAME = "Seguro de Vida Individual";
	public static final BigDecimal BASE_AMOUNT = BigDecimal.valueOf(100.0);
	public static final BigDecimal ANOTHER_BASE_AMOUNT = BigDecimal.valueOf(105.0);
	public static final String ID = UUID.randomUUID().toString();
	public static final BigDecimal AMOUNT_WITH_TAX = BigDecimal.valueOf(103.20);
	public static final BigDecimal ANOTHER_AMOUNT_WITH_TAX = BigDecimal.valueOf(108.36);

	public static ProductToUpsert buildProductToUpsert() {
		return new ProductToUpsert(PRODUCT_NAME, VIDA, BASE_AMOUNT);
	}
	
	public static ProductDomain buildProductDomainWithIdAndAmountWithTax() {
		return new ProductDomain(ID, PRODUCT_NAME, VIDA, BASE_AMOUNT, AMOUNT_WITH_TAX);
	}
	
	public static ProductDomain buildProductDomainWithoudIdAndAmountWithTax() {
		return new ProductDomain(PRODUCT_NAME, VIDA, BASE_AMOUNT);
	}
	
	public static ProductDomain buildProductDomainWithAnotherBaseAmountAndWithouId() {
		return new ProductDomain(PRODUCT_NAME, VIDA, ANOTHER_BASE_AMOUNT);
	}
	
	public static ProductEntity buildProductEntityWithAnotherBaseAmount() {
		return new ProductEntity(ID, PRODUCT_NAME, VIDA.name(), ANOTHER_BASE_AMOUNT, ANOTHER_AMOUNT_WITH_TAX);
	}
	
	public static ProductRequestDto buildProductRequestDto() {
		return new ProductRequestDto(PRODUCT_NAME, VIDA, BASE_AMOUNT);
	}
	
	public static ProductEntity buildProductEntity() {
		return new ProductEntity(ID, PRODUCT_NAME, VIDA.name(), BASE_AMOUNT, AMOUNT_WITH_TAX);
	}
	
	public static String getInsuranceAutoRequest() {
		return """
				{
					"nome": "Seguro Auto Individual",
					"categoria": "AUTO",
					"preco_base": 50.00
				}
				""";
	}
	
	public static String getInsuranceVidaRequest() {
		return """
				{
					"nome": "Seguro de Vida Individual",
					"categoria": "VIDA",
					"preco_base": 100.00
				}
				""";
	}
}
