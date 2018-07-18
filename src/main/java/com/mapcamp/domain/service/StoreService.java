package com.mapcamp.domain.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.JsonNode;
import com.mapcamp.domain.entity.Store;

public interface StoreService {

    Store save(Store store);
	
	Store findOneOrNew(String name);

    Page<Store> findAll(Pageable pageable);
	
	Store getNodeList(String url);
	
	Store viewJsonNode(JsonNode nodeList);

}
