package com.insurance.products.entrypoint.dto.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductResponseDto(
		String id,
		@JsonProperty("nome") String name,
		@JsonProperty("categoria") String category,
		@JsonProperty("preco_base") BigDecimal baseAmount,
		@JsonProperty("preco_tarifado") BigDecimal amountWithTax		
) {

	@Override
	public String toString() {
		return "ProductResponseDto [id=" + id + ", name=" + name + ", category=" + category + ", baseAmount="
				+ baseAmount + ", amountWithTax=" + amountWithTax + "]";
	}
}