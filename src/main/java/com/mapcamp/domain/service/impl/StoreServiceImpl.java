package com.mapcamp.domain.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.Store;
import com.mapcamp.domain.repository.StoreRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreRepository storeRepository;

	private List<Map<String, String>> resList;

	@Override
	public Store preSave(Map<String, String> select) {
		Store store = new Store();
		store.setLat(select.get("lat"));
		store.setLon(select.get("lon"));
		store.setName(select.get("name"));
		if(select.get("budget") != null && !select.get("budget").equals("")) {
			store.setPrice(Long.parseLong(select.get("budget")));
		}
		if(select.get("budget") != null && !select.get("budget").equals("")) {
			store.setTime(select.get("opentime"));
		}
		store.setUrl(select.get("url"));
		save(store);
		return store;
	}

	@Override
	public Store save(Store store){
		return storeRepository.save(store);
	}



	@Override
	public Store findOneOrNew(String name){
		Store store = storeRepository.findByName(name);
		if(store == null) store = new Store();
		return store;
	}

	@Override
	public Page<Store> findAll(Pageable pageable){
		return storeRepository.findAll(pageable);
	}

	//    
	//    
	//    @Override
	//    public Store findOne(Long id){
	//        return storeRepository.findOne(id);
	//    }


	@Override
	public Store getNodeList(String url) {
		try {
			URL restSearch = new URL(url);
			HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
			http.setRequestMethod("GET");
			http.connect();
			//   			// Jackson
			ObjectMapper mapper = new ObjectMapper();
			viewJsonNode(mapper.readTree(http.getInputStream()));

		} catch (Exception e) {
			// TODO: 例外を考慮していません
		}
		return null;
	}

	@Override
	public Store viewJsonNode(JsonNode nodeList) {
		if (nodeList != null) {
			// トータルヒット件数
			String hitcount = "total:" + nodeList.path("total_hit_count").asText();
			System.out.println(hitcount);
			// restのみ取得
			JsonNode restList = nodeList.path("rest");
			Iterator<JsonNode> rest = restList.iterator();
			// 店舗番号、店舗名、経緯度、予算、営業時間、URLを出力

			resList = new ArrayList<Map<String, String>>();
			while (rest.hasNext()) {
				JsonNode r = rest.next();
				String id = r.path("id").asText();
				String name = r.path("name").asText();
				String latitude = r.path("latitude").asText();
				String longitude = r.path("longitude").asText();
				String budget = r.path("budget").asText();
				String opentime = r.path("opentime").asText();
				String url = r.path("url").asText();

				setResponse(id, name, latitude, longitude, budget, opentime, url);
			}
		}
		return null;
	}

	public List<Map<String, String>> setResponse(String id, String name, String lat, String lon, String budget, String opentime, String url) {
		Map<String, String> list = new HashMap<String, String>();

		if(id != null) {
			list.put("id", id);
		}
		if(name != null) {
			list.put("name", name);
		}
		if(lat != null) {
			list.put("lat", lat);
		}
		if(lon != null) {
			list.put("lon", lon);
		}
		if(budget != null) {
			list.put("budget", budget);
		}
		if(opentime != null) {
			list.put("opentime", opentime);
		}
		if(url != null) {
			list.put("url", url);
		}
		resList.add(list);

		return resList;
	}

	@Override
	public List<Map<String, String>> getResponse(){
		if(resList == null) {
			resList = null;
		}
		return resList;
	}

	@Override
	public Map<String, String> getOneResponse(String id){
		if(resList != null){
			for(Map<String, String> store: resList) {
				if(store.get("id").equals(id)) {
					return store;
				}
			}
		}
		return null;
	}


}

