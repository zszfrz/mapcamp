package com.mapcamp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.mapcamp.domain.entity.Store;

//import com.mapcamp.domain.service.store;
//import com.mapcamp.domain.service.string;

public interface StoreRepository {

	//@Query("SELECT p.store FROM POST p GROUP BY p.store ORDER BY COUNT(p.store) DESC")
	//page<store> findTop(Pageable pageable);

	List<Store>findALLByTitleLike(String Keyword);
}
