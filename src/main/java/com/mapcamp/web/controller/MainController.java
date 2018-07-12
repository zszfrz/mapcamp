package com.mapcamp.web.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.UserRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.security.LoginUserDetails;


@Controller
@SessionAttributes(names="list")
public class MainController {
	
	private List<Long> session_list;
	
	@Autowired
	private PostService postService;
	
	@ModelAttribute(name = "loginUser")
	public UserDetails setLoginUser(@AuthenticationPrincipal LoginUserDetails userCustom) {
	    return userCustom;
	}
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("list")List<Long> session_list) {
		List<Post> post_list = new ArrayList<Post>();
		for(Long l: session_list) {
			post_list.add(postService.findOne(l));
		}
		mav.addObject("wannago_list", post_list);
		mav.setViewName("/map"); //仮でmapに遷移
		return mav;
	}
	
	@ModelAttribute("list")
    public List<Long> setList(Long post_id){
		if(session_list == null)session_list = new ArrayList<Long>();
		session_list.add(post_id);
        return session_list;
    }
	
	
	
	@RestController
	public class MainRestController{
		
		@Autowired
		    private PostService postService;
		
		@RequestMapping(value = "/{post_id}/add", method = RequestMethod.GET)
		@ResponseBody
		public Post sendList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
			setList(post_id);
			return postService.findOne(post_id);
		}
	}
}
