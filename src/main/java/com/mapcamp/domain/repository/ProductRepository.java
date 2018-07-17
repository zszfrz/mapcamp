package com.mapcamp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapcamp.domain.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByShopUrl(String shopUrl);
	List<Product> findAllByShopUrlLike(String keyword);
}
