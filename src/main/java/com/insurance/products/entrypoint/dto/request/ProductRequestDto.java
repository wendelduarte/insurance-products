package com.insurance.products.entrypoint.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.domain.enums.Category;

public record ProductRequestDto(
	@JsonProperty("nome") String name,
	@JsonProperty("categoria") Category category,
	@JsonProperty("preco_base") BigDecimal baseAmount) {
	
	public ProductDomain toDomain() {
		return new ProductDomain(name, category, baseAmount);
	}
}