package com.insurance.products.entrypoint.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.insurance.products.core.usecase.UpsertProductUseCase;
import com.insurance.products.entrypoint.controller.ProductController;
import com.insurance.products.entrypoint.dto.request.ProductRequestDto;
import com.insurance.products.entrypoint.dto.response.ProductResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
	
	private final UpsertProductUseCase upsertProductUseCase;

	@Override
	public ProductResponseDto upsertProduct(ProductRequestDto product) {
		log.info("Received product to upsert: {}", product);
		var productToResponse = upsertProductUseCase.execute(product.toDomain());
		var productResponse = new ProductResponseDto(
				productToResponse.id(),
				productToResponse.name(),
				productToResponse.category().name(),
				productToResponse.baseAmount(),
				productToResponse.amountWithTax()
		);
		log.info("Finish to process product upsert product to return: {}", productResponse);
		return productResponse;
	}
}