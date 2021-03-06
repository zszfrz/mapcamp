package com.mapcamp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.service.PostService;

@Controller
public class SearchController {

	
	@Autowired
	private PostService postService;

	private List<Post> searchShows;
	
	
	public List<Post> getSearch(){
		
		return searchShows;
	}

	// テンプレートから値を取得し検索
	@RequestMapping(value = "/search_all", method = RequestMethod.POST)
	public ModelAndView searchAll(@RequestParam("Param") String Param, ModelAndView mav) {
		if (Param == "") {
			mav.setViewName("redirect:/");
		} else {
			mav.addObject("Param", Param);
			searchShows = (postService.findAllByAllLike(Param));
			mav.addObject("searchShows", searchShows);
			mav.setViewName("search");
		}
		return mav;
	}
	


	@GetMapping("search")
	public ModelAndView search_show(ModelAndView mav) {
		mav.setViewName("/");
//		//mav.addObject("searchShows", searchShows);
//		redirectAttributes.addFlashAttribute("message", "新規レコードを作成しました");
//		System.out.println(mav);
		return mav;
	}

}
