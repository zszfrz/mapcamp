package com.mapcamp.domain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.Store;


public interface PostService {

	Post findOneOrNew(String shopname);

    Page<Post> findAll(Pageable pageable);

    Post findOne(Long post_id);
    
	Post findByShopname(String shopname);
	
//	List<Post> findAllByOrderByNowdateDesc();
   
    //postとuserを結びつけて保存　userIdを受け取れるように Long postId追加？
	Post save(Post post,Long userId,MultipartFile file, Store store) throws IOException;
	Post save(Post post);
	byte[] downloadImage(Long postId) throws IOException;
	

	List<Post> findAllByCategoryLike(String param);
	

}
