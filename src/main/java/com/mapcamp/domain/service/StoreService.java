package com.mapcamp.domain.service;

import java.util.List;

import com.mapcamp.domain.entity.Store;

public interface StoreService {

	//Page<Store> findTop5();
	List<Store>findALLByTitleLike(String Keyword);



}
