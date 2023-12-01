package com.insurance.products.dataprovider.database.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.products.dataprovider.database.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
	
	Optional<ProductEntity> findByNameAndCategory(String name, String category);

}