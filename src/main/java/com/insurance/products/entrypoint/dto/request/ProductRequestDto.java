package com.insurance.products.entrypoint.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insurance.products.core.domain.dto.ProductToUpsert;
import com.insurance.products.core.domain.enums.Category;

public record ProductRequestDto(
	@JsonProperty("nome") String name,
	@JsonProperty("categoria") Category category,
	@JsonProperty("preco_base") BigDecimal baseAmount) {
	
	public ProductToUpsert toDomain() {
		return new ProductToUpsert(name, category, baseAmount);
	}

	@Override
	public String toString() {
		return "ProductRequestDto [name=" + name + ", category=" + category + ", baseAmount=" + baseAmount + "]";
	}
	
	
}