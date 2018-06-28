package com.mapcamp.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.mapcamp.domain.entity.Post;


public interface PostService {

	Post findOneOrNew(String shopname);

    Page<Post> findAll(Pageable pageable);

    Post findOne(Long id);

    //List<Post> findAllByTitleLike(String keyword);
	
	Post save(Post post);
	
	
}
