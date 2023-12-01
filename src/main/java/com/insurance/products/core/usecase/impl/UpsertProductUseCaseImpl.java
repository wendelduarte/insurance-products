package com.insurance.products.core.usecase.impl;

import org.springframework.stereotype.Service;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.gateway.database.ProductGateway;
import com.insurance.products.core.usecase.UpsertProductUseCase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpsertProductUseCaseImpl implements UpsertProductUseCase {

	private final ProductGateway productGateway;
	
	@Override
	public ProductDomain execute(ProductDomain product) {
		return productGateway.upsert(product);
	}
}