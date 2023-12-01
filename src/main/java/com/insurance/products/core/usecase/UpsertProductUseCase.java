package com.insurance.products.core.usecase;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.domain.dto.ProductToUpsert;

public interface UpsertProductUseCase {

	ProductDomain execute(ProductToUpsert product);
}
