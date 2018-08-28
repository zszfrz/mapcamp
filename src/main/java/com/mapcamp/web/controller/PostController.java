package com.mapcamp.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.api.Select;
import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.Store;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.service.StoreService;
import com.mapcamp.security.LoginUserDetails;
import com.mapcamp.web.form.PostForm;

@Controller
public class PostController {

	Map<String, String> shop;

	@Autowired
	private PostService postService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private MainController mainController;

	@GetMapping("/posts/new")
	public String newPost(PostForm form,
			Model model) {
		Select select = new Select();
		model.addAttribute("selectform", select);
		return "posts/new";
	}    

	// ユーザーとPostの処理
	@PostMapping("/posts/new") // "/users/{userId}/posts"
	public String createPost(@Validated PostForm form, BindingResult result, Model model, Post newPost,
			@AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException {
		if (result.hasErrors()) {
			return newPost(form, model);
		}
		Store store = storeService.preSave(shop);

		Post post = new Post();
		BeanUtils.copyProperties(form, post);
		post.setNowDate();
		postService.save(post, loginUserDetails.getUserId(), form.getFile(), store);
		return "posts/create";
	}

	@GetMapping("/posts/{postId}/edit")
	public String editPost(@PathVariable Long postId, PostForm form, Model model) {
		delShop();
		Post post = postService.findOne(postId);
		model.addAttribute("post", post);
		Select sel = new Select();
		model.addAttribute("selectform", sel);
		return "posts/edit";
	}

	@PostMapping(value = "/posts/{postId}/edit")
	public String updatePost(@Validated PostForm form, @PathVariable Long postId, BindingResult result, Model model,
			Post editPost, @AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException {
		Post post = postService.findOne(postId);
		if (!post.getUser().getId().equals(loginUserDetails.getUserId())) {
			return "redirect:/posts/" + postId + "/edit";
		}
		BeanUtils.copyProperties(form, post);
		Store store = storeService.preSave(shop);
		if(shop.get("name") != null) {
			post.setStores(store);
		}
		postService.save(post);
		return "posts/update";
	}

	@PostMapping(value = "/posts/{postId}/delete")
	public String deletePost(@PathVariable Long postId, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		Post post = postService.findOne(postId);
		if (!post.getUser().getId().equals(loginUserDetails.getUserId())) {
			return "redirect:/";
		}
		
		mainController.deleteList(postId);
		postRepository.delete(post);
		
		return "posts/delete";
	}

	@RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
	ModelAndView show(@PathVariable Long postId, ModelAndView mav) {
		Post post = postService.findOne(postId);
		mav.addObject("post", post);
		mav.addObject("nowdate",post.getNowDate());
		mav.setViewName("posts/show");
		return mav;
	}

	@GetMapping("/posts/{id}/post-image.jpg")
	@ResponseBody
	public byte[] downloadImage(@PathVariable Long id) throws IOException {
		return postService.downloadImage(id);
	}

	public Map<String, String> setShop(String id){
		if(shop == null) {
			shop = new HashMap<String, String>();
		}
		if(id != null) {
			shop = storeService.getOneResponse(id);
		}
		return shop;
	}
	
	public Map<String, String> delShop(){
		shop = new HashMap<String, String>();
		return shop;
	}
}
