package com.mapcamp.web.controller;

import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.service.PostService;

@RestController
public class MapController{

	@Autowired
	//private PostService postService;

	@RequestMapping(value = "/latlon", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getLatLon(ModelAndView mav, @ModelAttribute("list")List<Long> list) {
		JSONObject obj = new JSONObject();
		try {

			for(Long id: list) {
				JSONObject obj2 = new JSONObject();
				obj2.put("sample", 1);
				//obj2.put("name", postService.findOne(id).getStore().getName());
				//obj2.put("lat", postService.findOne(id).getStore().getLat());
				//obj2.put("lon", postService.findOne(id).getStore().getLon());
				obj.put(id.toString(), obj2);

			}
		}
		catch(JSONException e){
			System.out.println(e);
		}
		return obj;
	}
}