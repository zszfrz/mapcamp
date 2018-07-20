package com.mapcamp.web.controller;


import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.repository.UserRepository;
import com.mapcamp.security.LoginUserDetails;

import org.json.JSONException;
import org.json.JSONObject;

@Controller
@SessionAttributes(names="list")
public class MainController {

	private List<Long> session_list;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostService postService;
//	@Autowired
  //  private UserRepository userRepository;
	
//	@Autowired
	  //  private PostRepository postRepository;
	
	
	@ModelAttribute(name = "loginUser")
    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        return loginUserDetails;
    }	
		
	

	@ModelAttribute(name = "loginUser")
	public UserDetails setLoginUser(@AuthenticationPrincipal LoginUserDetails userCustom) {
		return userCustom;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("list")List<Long> session_list) {
		List<Post> post_list = new ArrayList<Post>();
		if (session_list.get(0) != null) {
			for(Long l: session_list) {//session_listにはpostIdが入っている
				post_list.add(postService.findOne(l));
			}
		}
		mav.addObject("wannago_list", post_list);
		mav.setViewName("posts/main");
		return mav;
	}

	@ModelAttribute("list")
	public List<Long> setList(Long post_id){
		if(session_list == null)session_list = new ArrayList<Long>();
		session_list.add(post_id);
		return session_list;
	}

	@RequestMapping(value = "/route", method = RequestMethod.GET)
	public ModelAndView setRoute(ModelAndView mav) {
		mav.setViewName("/map/map");
		return mav;
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

		@RequestMapping(value = "/latlon", method = RequestMethod.POST)
		@ResponseBody
		public JSONObject getLatLon(@ModelAttribute("list")List<Long> list) {
			JSONObject obj = new JSONObject();
			try {

				for(Long id: list) {
					obj.put("id", id);
					JSONObject obj2 = new JSONObject();
					obj2.put("sample", 1);
					//obj2.put("name", postService.findOne(id).getStore().getName()); //storeがないため
					//obj2.put("lat", postService.findOne(id).getStore().getLat());
					//obj2.put("lon", postService.findOne(id).getStore().getLon());
					obj.put("store", obj2);

				}
			}
			catch(JSONException e){
				System.out.println(e);
			}
			return obj;
		}
	}
}
