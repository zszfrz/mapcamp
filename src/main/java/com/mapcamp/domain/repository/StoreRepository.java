package com.mapcamp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapcamp.domain.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	Store findByName(String name);
	List<Store> findAllByNameLike(String keyword);

}
