package com.insurance.products.core.domain.enums;

import java.math.BigDecimal;
import java.util.Arrays;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

	VIDA(BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.2), BigDecimal.valueOf(0.0)),
	AUTO(BigDecimal.valueOf(5.5), BigDecimal.valueOf(4.0), BigDecimal.valueOf(1.0)),
	VIAGEM(BigDecimal.valueOf(2.0), BigDecimal.valueOf(4.0), BigDecimal.valueOf(1.0)),
	RESIDENCIAL(BigDecimal.valueOf(4.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(3.0)),
	PATRIMONIAL(BigDecimal.valueOf(5.0), BigDecimal.valueOf(3.0), BigDecimal.valueOf(0.0));
	
	private static final BigDecimal PERCENTAGE_DIVISOR = BigDecimal.valueOf(100.0);
	
	private final BigDecimal iofPercentage;
	private final BigDecimal pisPercentage;
	private final BigDecimal cofinsPercentage;
	
	public BigDecimal getIof() {
		return iofPercentage.divide(PERCENTAGE_DIVISOR);
	}
	
	public BigDecimal getPis() {
		return pisPercentage.divide(PERCENTAGE_DIVISOR);
	}
	
	public BigDecimal getCofins() {
		return cofinsPercentage.divide(PERCENTAGE_DIVISOR);
	}
}