package com.mapcamp.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class PostForm {
	
	@NotBlank
	private String shopname;
	
	@NotBlank
	private String nickname;

	@NotNull
	@Min(1)
	@Max(10)
	private Integer yummy;
	
	@NotNull
	@Min(1)
	@Max(10)
	private Integer clean;
	
	
	@NotNull
	@Min(1)
	@Max(10)
	private Integer staff;
	
	
	@NotNull
	@Min(1)
	@Max(10)
	private Integer speed;
	

	@NotBlank
	private String comment;

	
	public String getShopname() {
		  return shopname;
	}
	public void setShopname(String shopname) {
		  this.shopname = shopname;
	}
	
	
  public String getNickname() {
  return nickname;
  }
  public void setNickname(String nickname) {
  this.nickname = nickname;
  }

  public Integer getYummy() {
	  return yummy;
  }
  public void setYummy(Integer yummy) {
	  this.yummy = yummy;
  }
  
  public Integer getClean() {
	  return clean;
  }
  public void setClean(Integer clean) {
	  this.clean = clean;
  }
  
  public Integer getStaff() {
	  return staff;
  }
  public void setStaff(Integer staff) {
	  this.staff = staff;
  }
  
  public Integer getSpeed() {
	  return speed;
  }
  public void setSpeed(Integer speed) {
	  this.speed = speed;
  }

  public String getComment() {
	  return comment;
  }
  public void setComment(String comment) {
	  this.comment = comment;
  }



	
}
