package com.mapcamp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.service.PostService;

@Controller
public class SearchController {

	@Autowired
	private PostRepository postRepository ;
	
	@Autowired
	private PostService postService;

	private List<Post> searchShows;
	
	
	public List<Post> getSearch(){
		
		return searchShows;
	}
	

	// 検索画面(main) 遷移画面(search)
	// コントローラーからテンプレートへ値を渡す GET
//	@RequestMapping(value = "/posts/main", method = RequestMethod.GET) // searchへ
//	public ModelAndView find(ModelAndView mav) {
//		// Post searchShow = postRepository.findOne(1L); //postid=1をsearchShowにセット
//		List<Post> searchShows = postRepository.findAll();// 複数取得したいのでList
//		mav.addObject("searchShows", searchShows);// "searchShow"に渡すデータをセットしビューへ ("識別子名(viewで使用できる)",変数)
//		System.out.println(mav);
//		mav.setViewName("search");// 使用するビューをセット
//		return mav;
//	}

	// テンプレートから値を取得し検索
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("Param") String Param, ModelAndView mav) {

		if (Param == "") {
			mav.setViewName("redirect:/");
		} else {
			
			mav.addObject("Param", Param);
			searchShows = postRepository.findAllByCategoryLike(Param);
			mav.addObject("searchShows", searchShows);
			mav.setViewName("search");
		}
		return mav;
	}

	
//	// 検索結果をsearchへ表示
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public ModelAndView login(ModelAndView mav) {
//		mav.setViewName("search");
//		mav.addObject("searchShows", searchShows);
//		System.out.println(mav);
//		return mav;
//	}

}
