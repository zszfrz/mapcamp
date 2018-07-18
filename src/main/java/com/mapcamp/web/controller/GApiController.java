package com.mapcamp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mapcamp.domain.service.impl.StoreServiceImpl;


@Controller
public class GApiController {
	        
	@Autowired
	private StoreServiceImpl storeServiceImpl;
	        
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
	
	String uri=gnaviRestUri+prmFormat+prmKeyid+prmLat+prmLon+prmRange+prmName;

	public void gnavi() {
		storeServiceImpl.getNodeList(uri);
	}
	
		
}
