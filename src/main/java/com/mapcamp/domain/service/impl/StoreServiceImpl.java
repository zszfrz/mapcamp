package com.mapcamp.domain.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapcamp.domain.entity.Store;
import com.mapcamp.domain.repository.StoreRepository;
import com.mapcamp.domain.service.StoreService;

@Service
@Transactional
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreRepository storeRepository;

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

    @Override
	public Store getNodeList(String url) {
    	try {
			URL restSearch = new URL(url);
			HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
			http.setRequestMethod("GET");
			http.connect();
//			// Jackson
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
			while (rest.hasNext()) {
				JsonNode r = rest.next();
				String id = r.path("id").asText();
				String name = r.path("name").asText();
				String latitude = r.path("latitude").asText();
				String longitude = r.path("longitude").asText();
				String budget = r.path("budget").asText();
				String opentime = r.path("opentime").asText();
				String url = r.path("url").asText();
				
				Store store = new Store();
				store.setId(Long.parseLong(id));
				store.setLat(Long.parseLong(latitude));
				store.setLon(Long.parseLong(longitude));
				store.setName(name);
				store.setPrice(Long.parseLong(budget));
				store.setTime(opentime);
				store.setUrl(url);
				return storeRepository.save(store);
			}
        }
		return null;
    }
}

