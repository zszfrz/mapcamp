package com.mapcamp.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapcamp.domain.entity.Product;
import com.mapcamp.domain.repository.ProductRepository;
import com.mapcamp.domain.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
    
    @Override
    public Product findOneOrNew(String shopUrl){
        Product product = productRepository.findByShopUrl(shopUrl);
        if(product == null) product = new Product();
        return product;
    }

    @Override
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

}