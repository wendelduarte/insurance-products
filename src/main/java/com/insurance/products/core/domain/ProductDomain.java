package com.insurance.products.core.domain;

import java.math.BigDecimal;

import com.insurance.products.core.domain.enums.Category;

import lombok.Builder;

@Builder
public record ProductDomain(
		String id,
		String name,
		Category category,
		BigDecimal baseAmount,
		BigDecimal amountWithTax
) {
	
	public ProductDomain(String name, Category category, BigDecimal baseAmount) {
		this(null,
				name, 
				category,
				baseAmount,
				calculateAmountWithTax(category, baseAmount));
	}

	private static BigDecimal calculateAmountWithTax(Category category, BigDecimal baseAmount) {
		var iofAmount = baseAmount.multiply(category.getIof());
		var pisAmount = baseAmount.multiply(category.getPis());
		var cofinsAmount = baseAmount.multiply(category.getCofins());
		return baseAmount.add(iofAmount).add(pisAmount).add(cofinsAmount).setScale(2);
	}
}