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

    Post findOne(Long post_id);

    //キーワード検索
    List<Post> findAllByTitleLike(String keyword);
	
    //postとuserを結びつけて保存　userIdを受け取れるように Long postId追加？
	Post save(Post post,Long userId,MultipartFile file) throws IOException;
	byte[] downloadImage(Long postId) throws IOException;
	
//	//saveメソッドでstoreIdを受け取れるようにしている
//	void save(Post post, Long store_id);
	

}
