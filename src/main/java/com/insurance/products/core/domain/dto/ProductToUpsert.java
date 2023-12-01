package com.insurance.products.core.domain.dto;

import java.math.BigDecimal;

import com.insurance.products.core.domain.enums.Category;

public record ProductToUpsert(
		String name,
		Category category,
		BigDecimal baseAmount
) {

}
