package com.mapcamp.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import javax.persistence.Table;

@Entity
@Table(name = "stores")
public class Store {

	
	@OneToMany(mappedBy = "stores")
    private List<Post> posts;
	

	@Id
	@GeneratedValue
	private Long id;

	private Long lat;

	private Long lon;

	private String name;

	private Long price;

	private String time;

	private String url;

	public Long getStoreId() {
		return id;
	}

	public void setStoreId(Long store_id) {
		this.id = id;
	}

	public Long getLat() {
		return lat;
	}

	public void setLat(Long lat) {
		this.lat = lat;
	}

	public Long getLon() {
		return lon;
	}

	public void setLon(Long lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	//add　PostとStore繋ぎたい
	public List<Post> getPosts(){
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts=posts;
	}

}
