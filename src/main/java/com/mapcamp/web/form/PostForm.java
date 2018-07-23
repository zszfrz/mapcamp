package com.mapcamp.web.form;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class PostForm {
	
	

	private MultipartFile file;
	
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
	private String text;
	
	@Column(nullable = false)
    private String category;
	
	public MultipartFile getFile() {
	    return file;
	}
	public void setFile(MultipartFile file) {
	    this.file = file;
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

  public String getText() {
	  return text;
  }
  public void setText(String text) {
	  this.text = text;
  }

  
  public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
  

	
}
