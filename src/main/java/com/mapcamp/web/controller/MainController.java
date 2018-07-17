package com.mapcamp.web.controller;


import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.security.LoginUserDetails;


@Controller
@SessionAttributes(names="list")
public class MainController {
	
	private List<Long> list;
	
	@Autowired
	private PostRepository postRepository;
//	@Autowired
  //  private UserRepository userRepository;
	
//	@Autowired
	  //  private PostRepository postRepository;
	
	
	
//	@ModelAttribute(name = "login_user")
/*	public UserDetails setLoginUser(@AuthenticationPrincipal LoginUserDetails userCustom) {
	    return userCustom;
	}*/
	
	  @ModelAttribute(name = "loginUser")
	    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
	        return loginUserDetails;
	    }
		

		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("list")List<Long> list){
		List<Post> posts = postRepository.findAll();
        mav.addObject("posts", posts);
	//	for(Long l: list) {
	//		post_list.add(postRepository.findOne(l));
		//}
	//	mav.addObject("wannago_list", list);
        //mav.addObject("login_user", loginUserDetails.getUserId());
	//	mav.addObject(loginUser);
		mav.setViewName("posts/main");
		return mav;
	}
	
	@ModelAttribute("list")
    public List<Long> setList(Long post_id){
		if(list == null)list = new ArrayList<Long>();
		list.add(post_id);
        return list;
    }
	
	
	
	@RestController
	public class MainRestController{
		
//		@Autowired
		  //  private PostRepository postRepository;
		
/*		@RequestMapping(value = "/{post_id}/add", method = RequestMethod.GET)
		@ResponseBody
		public Post sendList(@PathVariable("post_id") Long post_id, ModelAndView mav, @ModelAttribute("list")List<Long> list) {
			setList(post_id);
			return postRepository.findOne(post_id);
		}*/
	}
}
