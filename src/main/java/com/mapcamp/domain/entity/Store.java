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

	@Id
	@GeneratedValue
	private Long store_id;

	private Long lat;

	private Long lon;

	private String name;

	private Long price;

	private String time;

	private String url;
	
	//add
	@OneToMany(mappedBy="store")
	private List<Post> posts; 

	public Long getStoreId() {
		return store_id;
	}

	public void setStoreId(Long store_id) {
		this.store_id = store_id;
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
	
	//add　Postとreview繋ぎたい
	public List<Post> getPosts(){
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts=posts;
	}

	//評価の平均
	 public int average() {
	
	 double sum=0;
	
	 for (Post post : posts) {
	 sum+= post.getYummy();
	 }
	 
	 double average =posts.size()== 0 ? 0 :sum/posts.size();
	
	 return (int) Math.round(average);
	 }

}
