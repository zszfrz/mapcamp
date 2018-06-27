package com.mapcamp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.service.PostService;


@Controller
public class PostController {
	
	@Autowired
    private PostService postService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        String hello = "Hello, Spring Boot!";
        mav.addObject("hello", hello);
        mav.setViewName("posts/index");
        return mav;
    }
	
	@RequestMapping(value = "/posts/new", method = RequestMethod.GET)
	public ModelAndView newPost(ModelAndView mav) {
	    mav.setViewName("posts/new");
	    return mav;
	}
	

}
