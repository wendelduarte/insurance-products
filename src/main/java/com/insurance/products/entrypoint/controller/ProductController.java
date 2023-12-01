package com.insurance.products.entrypoint.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insurance.products.entrypoint.dto.request.ProductRequestDto;
import com.insurance.products.entrypoint.dto.response.ProductResponseDto;

@RequestMapping("/products")
public interface ProductController {
	
	@PostMapping
	ProductResponseDto upsertProduct(@RequestBody ProductRequestDto product);

}
