package com.insurance.products.dataprovider.database.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.insurance.products.core.domain.ProductDomain;
import com.insurance.products.core.domain.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
	private String category;
	private BigDecimal baseAmount;
	private BigDecimal amountWithTax;
	
	public ProductEntity(String name, String category, BigDecimal baseAmount, BigDecimal amountWithTax) {
		this.name = name;
		this.category = category;
		this.baseAmount = baseAmount;
		this.amountWithTax = amountWithTax;
	}
	
	public ProductDomain toDomain() {
		return new ProductDomain(
				id,
				name,
				Category.valueOf(category),
				baseAmount,
				amountWithTax
		);
	}
}