package com.mapcamp.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mapcamp.domain.entity.Product;

public interface ProductService {
	Product save(Product product);
	
	Product findOneOrNew(String shopUrl);

    Page<Product> findAll(Pageable pageable);
}
