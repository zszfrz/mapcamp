package com.mapcamp.domain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.mapcamp.domain.entity.Post;


public interface PostService {

	Post findOneOrNew(String shopname);

    Page<Post> findAll(Pageable pageable);

    Post findOne(Long id);

    //List<Post> findAllByTitleLike(String keyword);
	
	Post save(Post post,Long userId,MultipartFile file) throws IOException;
	byte[] downloadImage(Long postId) throws IOException;
	

}
