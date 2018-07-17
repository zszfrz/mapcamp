package com.mapcamp.web.controller;

import java.io.IOException;
import java.util.List;

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

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.security.LoginUserDetails;
import com.mapcamp.web.form.PostForm;

@Controller
public class PostController {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public ModelAndView index(ModelAndView mav) {
	// List<Post> posts = postRepository.findAll();
	// mav.addObject("posts", posts);
	// mav.setViewName("posts/main");
	// return mav;
	// }

	@GetMapping("/posts/new")
	public String newPost(PostForm form, Model model) {

		return "posts/new";
	}

	// ユーザーとPostの処理
	@PostMapping("/posts/new") // "/users/{userId}/posts"
	public String createPost(@Validated PostForm form, BindingResult result, Model model, Post newPost,
			@AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException {
		if (result.hasErrors()) {
			return newPost(form, model);
		}
		Post post = new Post();
		BeanUtils.copyProperties(form, post);
		postService.save(post, loginUserDetails.getUserId(), form.getFile());
		return "posts/create";
	}

	// storeIdとPostを繋ぎたい
	@PostMapping("/posts/{storeId}/new") // "/users/{userId}/posts"
	public String createPost(@PathVariable Long storeId, @Validated PostForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return newPost(storeId, form, model); //エラー　storeIDの型
		}
		Post post = new Post();
		BeanUtils.copyProperties(form, post);
		postService.save(post, storeId);
		return "redirect:/";
	}

	@GetMapping("/posts/{postId}/edit")
	public String editPost(@PathVariable Long postId, PostForm form, Model model) {
		Post post = postService.findOne(postId);
		model.addAttribute("post", post);
		return "posts/edit";
	}

	@PostMapping(value = "/posts/{postId}/edit")
	public String updatePost(@Validated PostForm form, @PathVariable Long postId, BindingResult result, Model model,
			Post editPost, @AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException {
		Post post = postRepository.findOne(postId);
		if (!post.getUser().getId().equals(loginUserDetails.getUserId())) {
			return "redirect:/posts/" + postId + "/edit";
		}
		BeanUtils.copyProperties(form, post);
		postRepository.save(post);
		return "posts/update";
	}

	@PostMapping(value = "/posts/{postId}/delete")
	public String deletePost(@PathVariable Long postId, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		Post post = postRepository.findOne(postId);
		if (!post.getUser().getId().equals(loginUserDetails.getUserId())) {
			return "redirect:/";
		}
		postRepository.delete(post);
		return "posts/delete";
	}

	@RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
	ModelAndView show(@PathVariable Long postId, ModelAndView mav) {
		//Post post = postRepository.findOne(postId); これ消してみて
		List<Post> post = PostRepository.findAllByTitleLike(); //追加してみた　エラー原因不明
		mav.addObject("post", post);
		mav.setViewName("posts/show");
		return mav;
	}

	@GetMapping("/posts/{id}/post-image.jpg")
	@ResponseBody
	public byte[] downloadImage(@PathVariable Long id) throws IOException {
		return postService.downloadImage(id);
	}

	// 追記 ProductIdでDBからproduct情報を取り出し、reviewにセット
	// @PostMapping("/products/{productId}/XXXX")
	// public String createReview(@PathVariable Long productId,
	// @Validated ReviwForm form,
	// BindingResult result,Model model) {
	//
	// if(result.hasEroors()) {
	// return newReview(productId,form,model);
	// }
	// Review review=new Review();
	// BeanUtils.copyProperties(form, review);
	// reviewService.save(review,productId);
	//
	// return "redirect:/";
	// }
}
