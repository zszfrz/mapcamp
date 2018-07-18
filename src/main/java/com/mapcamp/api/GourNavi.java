package com.mapcamp.api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mapcamp.domain.entity.Store;
import com.mapcamp.domain.service.StoreService;

public class GourNavi {
	
	@Autowired
    private static StoreService storeService;
	
	public static void main(String[] args) {
		// アクセスキー
		String acckey = "58a9cc097cca953276da1b8389ed535a";
		// 緯度
		String lat = "35.6280826";
		// 経度
		String lon = "139.7392073";
		// 範囲
		String range = "1";
		// 返却形式
		String format = "json";
		// エンドポイント
		String name = "中国料理 ";
		// エンドポイント
		String gnaviRestUri = "https://api.gnavi.co.jp/RestSearchAPI/20150630/";
		String prmFormat = "?format=" + format;
		String prmKeyid = "&keyid=" + acckey;
		String prmLat = "&latitude=" + lat;
		String prmLon = "&longitude=" + lon;
		String prmRange = "&range=" + range;
		String prmName = "&name=" + name;

		// URI組み立て
		StringBuffer uri = new StringBuffer();
		uri.append(gnaviRestUri);
		uri.append(prmFormat);
		uri.append(prmKeyid);
		uri.append(prmLat);
		uri.append(prmLon);
		uri.append(prmRange);
		uri.append(prmName);

		// API実行、結果を取得し出力
		getNodeList(uri.toString());
	}

	private static void getNodeList(String url) {
		try {
			URL restSearch = new URL(url);
			HttpURLConnection http = (HttpURLConnection) restSearch.openConnection();
			http.setRequestMethod("GET");
			http.connect();
			// Jackson
			ObjectMapper mapper = new ObjectMapper();
			viewJsonNode(mapper.readTree(http.getInputStream()));

		} catch (Exception e) {
			// TODO: 例外を考慮していません
		}
	}

	private static void viewJsonNode(JsonNode nodeList) {
		if (nodeList != null) {
			// トータルヒット件数
			String hitcount = "total:" + nodeList.path("total_hit_count").asText();
			System.out.println(hitcount);
			// restのみ取得
			JsonNode restList = nodeList.path("rest");
			Iterator<JsonNode> rest = restList.iterator();
			// 店舗番号、店舗名、最寄の路線、最寄の駅、最寄駅から店までの時間、店舗の小業態を出力
			Store store = new Store();
			while (rest.hasNext()) {
				JsonNode r = rest.next();
				String id = r.path("id").asText();
				String name = r.path("name").asText();
				String latitude = r.path("latitude").asText();
				String longitude = r.path("longitude").asText();
				String budget = r.path("budget").asText();
				String opentime = r.path("opentime").asText();
				String url = r.path("url").asText();
//				String line = r.path("access").path("line").asText();
//				String station = r.path("access").path("station").asText();
//				String walk = r.path("access").path("walk").asText() + "分";
//				String categorys = "";

//				for (JsonNode n : r.path("code").path("category_name_s")) {
//					categorys += n.asText();
//				}
				
				//Store store = storeService.findOneOrNew(name);
//				//Store store = new Store();
//				store.setStoreId(id);
//				store.setLat(latitude);
//				store.setLon(longitude);
//				store.setName(name);
//				store.setPrice(budget);
//				store.setTime(opentime);
//				store.setUrl(url);
				System.out.println(id + "¥t" + name + "¥t" + latitude + "¥t" + longitude + "¥t" + budget + "¥t" + opentime+ "¥t" + url);
				storeService.save(store);
				
				
			}
		}
	}
}
