package com.mapcamp.web.controller;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
@SessionAttributes(names="list")
public class MainController {
	
	private List<Long> list;
	
//	@Autowired
  //  private UserRepository userRepository;
	
//	@Autowired
	  //  private PostRepository postRepository;
	
//	@ModelAttribute(name = "login_user")
/*	public UserDetails setLoginUser(@AuthenticationPrincipal LoginUserDetails userCustom) {
	    return userCustom;
	}*/
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("list")List<Long> list) {
	//	List<Post> post_list = new ArrayList<Post>();
		for(Long l: list) {
	//		post_list.add(postRepository.findOne(l));
		}
	//	mav.addObject("wannago_list", list);
	//	mav.addObject(loginUser);
		mav.setViewName("/index");
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