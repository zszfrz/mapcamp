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



@Controller
public class SearchController {

	@Autowired
	private PostRepository postRepository;
	private Object searchShows;

	// @RequestMapping(value = "posts/main", method = RequestMethod.POST)
	// public ModelAndView index(ModelAndView mav) {
	// String keyword = null; // = "Hello, Spring Boot!";
	// Post post = postRepository.findOne(1L);
	// mav.addObject("keyword", keyword);// 識別子，ビューに渡したい変数
	// mav.setViewName("/search");
	// return mav;
	// }
	
	//検索画面(main) 遷移画面(search)
	//コントローラーからテンプレートへ値を渡す GET 
	@RequestMapping(value="/posts/main",method = RequestMethod.GET) //searchへ
	public ModelAndView find(ModelAndView mav) {
		//Post searchShow = postRepository.findOne(1L); //postid=1をsearchShowにセット
		List<Post> searchShow = postRepository.findAll();//複数取得したいのでList
		mav.addObject("searchShow",searchShow);//"searchShow"に渡すデータをセットしビューへ ("識別子名(viewで使用できる)",変数)
		System.out.println(mav);
		mav.setViewName("search");//使用するビューをセット
		return mav;
	}
	
	//テンプレートから値を取得し検索
	@RequestMapping(value="/posts/main",method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("value")String Param,ModelAndView mav) {
		mav.setViewName("/posts/main");
		
		 if(Param ==""){
			    mav =new ModelAndView("redirect:/posts/main");
			    }else{
			        System.out.println("param"+Param);
			        mav.addObject("value",Param);
			        List<Post> searchShows=postRepository.findBycategoryLike(Param);
			        System.out.println(searchShows);
			        mav.addObject("searchShows",searchShows);
			    }
			    System.out.println(mav);
			    mav =new ModelAndView("redirect:/search");
		
		return mav;
	}
	
	//検索結果をsearchへ表示
	@RequestMapping(value="search",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView mav){
	    mav.setViewName("search");
	    mav.addObject("searchShows",searchShows);
	    System.out.println(mav);
	    return mav;
	}
	
	
}
