package com.mapcamp.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;

public class PostServiceImpl implements PostService{

	@Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post product){
        return postRepository.save(product);
    }
	
	
}
