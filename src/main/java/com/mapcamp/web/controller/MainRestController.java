package com.mapcamp.web.controller;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.service.PostService;

@RestController
public class MainRestController{

	@Autowired
	private PostService postService;

	@Autowired
	private MainController mainController;

	@RequestMapping(value = "/{post_id}/add", method = RequestMethod.GET)
	@ResponseBody
	public Post sendList(@PathVariable("post_id") Long post_id, ModelAndView mav) {
		mainController.setList(post_id);
		return postService.findOne(post_id);
	}

	@RequestMapping(value = "/latlon", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Map<String, Object>> getLatLon(@ModelAttribute("list")List<Long> list) {
		ArrayList<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

		try {			
			for(Long id: list) {
				Map<String, Object> obj1 = new HashMap<String, Object>();
				obj1.put("id", id); 
				Map<String, Object> obj2 = new HashMap<String, Object>();
				//	obj2.put("name", "とり"); //sample
				//	obj2.put("lat", 35.662613); //sample
				//	obj2.put("lon", 139.780453); //sample
				obj2.put("name", postService.findOne(id).getStore().getName()); //storeがないため
				obj2.put("lat", postService.findOne(id).getStore().getLat());
				obj2.put("lon", postService.findOne(id).getStore().getLon());
				obj1.put("store", obj2);
				map.add(obj1);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return map;
	}
}
