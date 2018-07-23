package com.mapcamp.domain.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.service.StoreService;
import com.mapcamp.domain.service.UserService;



@Service
@Transactional
public class PostServiceImpl implements PostService{

	@Autowired
    private PostRepository postRepository;
	
	@Autowired
    private PostService postService;
	
	@Autowired
    private UserService userService;

//    @Override
//    public Post save(Post post){
//        return postRepository.save(post);
//    }
	
    @Override
    public Post findOneOrNew(String shopname){
        Post post = postService.findByShopname(shopname);
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
    
    @Override
    public Post findByShopname(String shopname){
        return postService.findByShopname(shopname);
    }
    
//    @Override 
//    public List<Post> findAllByOrderByNowdateDesc(){
//    	return postService.findAllByOrderByNowdateDesc();
//    }

    //userIdでDBからUser情報を取り出し、postにセット ,Long postId
    @Override
    public Post save(Post post, Long userId, MultipartFile file) throws IOException {
        //post.setPost(postService.findOne(postId));
        post.setUser(userService.findOne(userId));
        post = postRepository.save(post);
        if (!file.isEmpty()) {
            post.setImage(uploadPostImage(file, post.getId()));  // ②
        }
        return post;
    }
    
    private String uploadPostImage(MultipartFile file, Long postId) throws IOException {
        Format formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = formatter.format(Calendar.getInstance().getTime()) + "_post-image.jpg";
        Path path = Paths.get("upload", "posts", postId.toString(), fileName);
        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path);
        return fileName;
    }
    
    @Override
    public byte[] downloadImage(Long postId) throws IOException {
        Post post = postRepository.findOne(postId);  // ①
        Path path = Paths.get("upload", "posts", post.getId().toString(), post.getImage());  // ②
        return Files.readAllBytes(path);  // ③
    }
    
    
//    //検索部分の処理
//    @Override
//    public List<Post> findAllByTitleLike(String keyword) {
//        return postRepository.findAllByTitleLike("%" + keyword + "%");
//    }
    
//    //引数：storeIdでDBからStoreを取り出し、Postにセット
//    @Override
//    public void save(Post post,Long store_id) {
//    	post.setStoreId(StoreService.findOne(store_id));//エラー
//    	postRepository.save(post);
//    }
	
}
