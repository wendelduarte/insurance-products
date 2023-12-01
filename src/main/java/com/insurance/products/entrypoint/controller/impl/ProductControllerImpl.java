package com.insurance.products.entrypoint.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.insurance.products.core.usecase.UpsertProductUseCase;
import com.insurance.products.entrypoint.controller.ProductController;
import com.insurance.products.entrypoint.dto.request.ProductRequestDto;
import com.insurance.products.entrypoint.dto.response.ProductResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
	
	private final UpsertProductUseCase upsertProductUseCase;

	@Override
	public ProductResponseDto upsertProduct(ProductRequestDto product) {
		var productToResponse = upsertProductUseCase.execute(product.toDomain());
		return new ProductResponseDto(
				productToResponse.id(),
				productToResponse.name(),
				productToResponse.category().name(),
				productToResponse.baseAmount(),
				productToResponse.amountWithTax()
		);
	}
}