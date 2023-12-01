package com.insurance.products.dataprovider.database.gateway;

import org.springframework.stereotype.Component;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.gateway.database.ProductGateway;
import com.insurance.products.dataprovider.database.entities.ProductEntity;
import com.insurance.products.dataprovider.database.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

	private final ProductRepository productRepository;

	@Override
	public ProductDomain upsert(ProductDomain product) {
		var productFromBase = productRepository.findByNameAndCategory(product.name(), product.category().name());
		
		if(productFromBase.isPresent()) {
			var updatedProduct = new ProductEntity(
					productFromBase.get().getId(),
					product.name(),
					product.category().name(),
					product.baseAmount(),
					product.amountWithTax());
			return productRepository.save(updatedProduct).toDomain();
		}
		
		var newProduct = new ProductEntity(product.name(), product.category().name(), product.baseAmount(), product.amountWithTax());
		return productRepository.save(newProduct).toDomain();
	}
}