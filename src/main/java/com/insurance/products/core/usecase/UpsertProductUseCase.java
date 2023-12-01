package com.insurance.products.core.usecase;

import com.insurance.products.core.domain.ProductDomain;

public interface UpsertProductUseCase {

	ProductDomain execute(ProductDomain product);
}
