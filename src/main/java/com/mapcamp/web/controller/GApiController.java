package com.mapcamp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.service.StoreService;

public class GApiController {

	@Autowired
	private StoreService storeService;

	@PostMapping("/select")
	public ModelAndView gnavinew(ModelAndView mav, @ModelAttribute String text) {
		String acckey = "58a9cc097cca953276da1b8389ed535a";
		String format = "json";
		String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
		String prmFormat = "?format=" + format;
		String prmKeyid = "&keyid=" + acckey;
		//		String prmLat = "&latitude=" + lat;
		//		String prmLon = "&longitude=" + lon;
		//		String prmRange = "&range=" + range;
		String prmName = "&name=" + text;

		// URI組み立て
		String uri = gnaviRestUri + prmFormat + prmKeyid + prmName;
		
		storeService.getNodeList(uri);
		
		mav.addObject("response", storeService.getResponse());
		mav.setViewName("posts/select");
		return mav;
	}
	
	@GetMapping("/new/select/{id}")
	public ModelAndView newConfirm(ModelAndView mav, @PathVariable("id") String id) {	
		mav.addObject("response", storeService.getOneResponse(id));
		mav.setViewName("posts/new");
		return mav;
	}
	
	@GetMapping("/edit/select/{id}")
	public ModelAndView editConfirm(ModelAndView mav, @PathVariable("id") String id) {	
		mav.addObject("response", storeService.getOneResponse(id));
		mav.setViewName("posts/edit");
		return mav;
	}
}
