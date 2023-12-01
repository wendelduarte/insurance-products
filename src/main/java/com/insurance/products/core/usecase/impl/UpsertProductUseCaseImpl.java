package com.insurance.products.core.usecase.impl;

import org.springframework.stereotype.Service;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.domain.dto.ProductToUpsert;
import com.insurance.products.core.gateway.database.ProductGateway;
import com.insurance.products.core.usecase.UpsertProductUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpsertProductUseCaseImpl implements UpsertProductUseCase {

	private final ProductGateway productGateway;
	
	@Override
	public ProductDomain execute(ProductToUpsert product) {
		log.info("Processing upsert product for name=[{}] and category=[{}]", product.name(), product.category());
		return productGateway.upsert(new ProductDomain(
				product.name(), 
				product.category(), 
				product.baseAmount())
		);
	}
}