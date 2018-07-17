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
	private String store_id;

	private String lat;

	private String lon;

	private String name;

	private String price;

	private String time;

	private String url;

	public String getStoreId() {
		return store_id;
	}

	public void setStoreId(String store_id) {
		this.store_id = store_id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
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

	// add Postとstore繋ぎたい
	// 1対多
	@OneToMany(mappedBy = "store")
	private List<Post> posts;

	public List<Post> getReviews() {
		return posts;
	}

	public void setReviews(List<Post> posts) {
		this.posts = posts;
	}

	// 評価の平均
	public int average_yummy() {
		double sum = 0;

		for (Post post : posts) {
			sum += post.getYummy();
		}

		// post.size=0が trueの時，average=0
		// falseの時 sum / posts.size()
		double average = posts.size() == 0 ? 0 : sum / posts.size();

		return (int) Math.round(average);
	}
	
	public int average_clean() {
		double sum = 0;

		for (Post post : posts) {
			sum += post.getClean();
		}

		// post.size=0が trueの時，average=0
		// falseの時 sum / posts.size()
		double average = posts.size() == 0 ? 0 : sum / posts.size();

		return (int) Math.round(average);
	}
	
	public int average_staff() {
		double sum = 0;

		for (Post post : posts) {
			sum += post.getStaff();
		}

		// post.size=0が trueの時，average=0
		// falseの時 sum / posts.size()
		double average = posts.size() == 0 ? 0 : sum / posts.size();

		return (int) Math.round(average);
	}
	
	public int average_speed() {
		double sum = 0;

		for (Post post : posts) {
			sum += post.getSpeed();
		}

		// post.size=0が trueの時，average=0
		// falseの時 sum / posts.size()
		double average = posts.size() == 0 ? 0 : sum / posts.size();

		return (int) Math.round(average);
	}

}
