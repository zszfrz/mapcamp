package com.mapcamp.web.controller;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.repository.PostRepository;

@Controller
@SessionAttributes(names="list")
public class MainController {

	private List<Long> session_list;

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private SearchController searchController;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("list")List<Long> session_list) {
		
		List<Post> posts = postRepository.findAllByOrderByNowdateDesc();
		List<Post> post_list = new ArrayList<Post>();

		if (session_list != null && session_list.size() > 0 && session_list.get(0) != null) {
			for(Long l: session_list) {//session_listにはpostIdが入っている
				post_list.add(postService.findOne(l));
			}
		}
		
		
		for(Post a : post_list){
		    for(Post b : posts){
		        if(a.equals(b)){
		            posts.remove(a);
		            break;
		        }
		    }
		}
		
		mav.addObject("posts", posts);
		mav.addObject("wannago_list", post_list);
		mav.setViewName("posts/main");
		return mav;
	}

	@ModelAttribute("list")
	public List<Long> setList(Long post_id){
		if(session_list == null)session_list = new ArrayList<Long>();
		if(post_id != null) {
		session_list.add(post_id);
		}
		return session_list;
	}
	
	
	public List<Long> getList(){
		return session_list;
	}
	

	@ModelAttribute("list")
	public List<Long> deleteList(Long post_id){
		if(post_id != null) {
		session_list.remove(post_id);
		}
		return session_list;
	}
	

	@RequestMapping(value = "/route", method = RequestMethod.GET)
	public ModelAndView setRoute(ModelAndView mav) {
		mav.setViewName("map/map");
		return mav;
	}

	@RequestMapping(value = "/{post_id}/add", method = RequestMethod.POST)
	@ResponseBody
	public  ModelAndView sendList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
		setList(post_id);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value = "/{post_id}/delete", method = RequestMethod.POST)
	@ResponseBody
	public  ModelAndView deleteList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
		deleteList(post_id);
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value = "/{post_id}/up", method = RequestMethod.POST)
	@ResponseBody
	public  ModelAndView upList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
			if(post_id != null) {
				int index = session_list.indexOf(post_id);//指定されたPostのindex番号を特定
				if(index != 0) {
					Long id = session_list.get(index-1);//upしたいので1つ前のPostを一旦避難
				session_list.set(index-1, post_id);//1つ前にコピー
				session_list.set(index, id);//避難しておいたものをindexの場所に入れる
				}
			}
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value = "/{post_id}/down", method = RequestMethod.POST)
	@ResponseBody
	public  ModelAndView downList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
		if(post_id != null) {
			int index = session_list.indexOf(post_id);//指定されたPostのindex番号を特定
			if(!session_list.get(index).equals(session_list.get(session_list.size()-1))) {
				Long id = session_list.get(index+1);//downしたいので1つ後のPostを一旦避難
			session_list.set(index+1, post_id);//1つ前にコピー
			session_list.set(index, id);//避難しておいたものをindexの場所に入れる
			}
		}
		mav.setViewName("redirect:/");
		return mav;
	}
}
