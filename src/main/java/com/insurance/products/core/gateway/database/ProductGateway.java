package com.insurance.products.core.gateway.database;

import com.insurance.products.core.domain.ProductDomain;

public interface ProductGateway {
	
	ProductDomain upsert(ProductDomain product);

}