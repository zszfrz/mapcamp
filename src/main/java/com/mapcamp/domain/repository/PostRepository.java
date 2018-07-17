package com.mapcamp.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapcamp.domain.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	
	Post findByShopname(String shopname);

	//検索ワード
    List<Post> findAllByTitleLike(String keyword);
	
    List<Post> findAll();
}
