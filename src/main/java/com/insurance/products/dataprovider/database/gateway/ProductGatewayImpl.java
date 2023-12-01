package com.insurance.products.dataprovider.database.gateway;

import org.springframework.stereotype.Component;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.gateway.database.ProductGateway;
import com.insurance.products.dataprovider.database.entities.ProductEntity;
import com.insurance.products.dataprovider.database.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductGatewayImpl implements ProductGateway {

	private final ProductRepository productRepository;

	@Override
	public ProductDomain upsert(ProductDomain product) {
		log.info("Getting product name=[{}] and category=[{}] from database.", product.name(), product.category());
		var productFromBase = productRepository.findByNameAndCategory(product.name(), product.category().name());
		
		if(productFromBase.isPresent()) {
			log.info("Product is already in database, updating product. Product id=[{}]", productFromBase.get().getId());
			
			var updatedProduct = new ProductEntity(
					productFromBase.get().getId(),
					product.name(),
					product.category().name(),
					product.baseAmount(),
					product.amountWithTax());
			return productRepository.save(updatedProduct).toDomain();
		}
		
		log.info("Product name=[{}] and category=[{}] is not in database, creating product.", product.name(), product.category());
		var newProduct = new ProductEntity(product.name(), product.category().name(), product.baseAmount(), product.amountWithTax());
		return productRepository.save(newProduct).toDomain();
	}
}