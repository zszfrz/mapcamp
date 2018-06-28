package com.mapcamp.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.web.form.PostForm;



@Controller
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
    private PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        String hello = "Hello, Spring Boot!";
        mav.addObject("hello", hello);
        mav.setViewName("posts/index");
        return mav;
    }
	
//	@RequestMapping(value = "/posts/new", method = RequestMethod.GET)
//	public ModelAndView newPost(ModelAndView mav) {
//	    mav.setViewName("posts/new");
//	    return mav;
//	}
	
	@GetMapping("/posts/new")
    public String newPost(PostForm form,
                            Model model) {
        
        return "posts/new";
    }
    
	
	@PostMapping("/posts/new")
	public String createPost(@Validated PostForm form,
	                           BindingResult result,
	                           Model model,Post newPost) {
//	    if (result.hasErrors()) {
//	        return newPost( form, model);
//	    }
	    Post post = new Post();
        BeanUtils.copyProperties(form, post);
        postService.save(post);
        return "posts/create";
	}
	
//	@RequestMapping(value = "/posts/new", method = RequestMethod.POST)
//    public ModelAndView createPost(Post newPost, ModelAndView mav) {
//        postRepository.saveAndFlush(newPost);
//        mav.setViewName("posts/create");
//        return mav;
   

}

