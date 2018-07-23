package com.mapcamp.domain.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.Store;

public interface StoreService {

    Store save(Store store);
	
	Store findOneOrNew(String name);

    Page<Store> findAll(Pageable pageable);
    
    //Store fineOne(Long store_id);

}
