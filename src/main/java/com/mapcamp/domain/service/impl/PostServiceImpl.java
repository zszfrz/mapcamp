package com.mapcamp.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;



@Service
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post){
        return postRepository.save(post);
    }
	
    @Override
    public Post findOneOrNew(String shopname){
        Post post = postRepository.findByShopname(shopname);
        if(post == null) post = new Post();
        return post;
    }

    @Override
    public Page<Post> findAll(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @Override
    public Post findOne(Long id){
        return postRepository.findOne(id);
    }

//    @Override
//    public List<Post> findAllByTitleLike(String keyword) {
//        return postRepository.findAllByTitleLike("%" + keyword + "%");
//    }
	
}
