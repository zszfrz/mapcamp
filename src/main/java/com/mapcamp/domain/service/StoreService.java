package com.mapcamp.domain.service;


import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mapcamp.domain.entity.Store;

public interface StoreService {

	Store save(Store store);

	Store findOneOrNew(String name);

	Page<Store> findAll(Pageable pageable);

	//Store fineOne(Long store_id);

	Store getNodeList(String url);

	Store viewJsonNode(JsonNode nodeList);
	
	List<Map<String, String>> getResponse();

	public Map<String, String> getOneResponse(String id);
	
	public Store preSave(Map<String, String> select);
}
