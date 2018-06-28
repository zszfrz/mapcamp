package com.mapcamp.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

	
	
	@Entity
	@Table(name = "posts")
	public class Post{
		
		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		
		@Column(nullable = false)
		private String shopname;
		
		@Column(nullable = false)
		private String nickname;
		
		
		@Column(nullable = false, columnDefinition = "TEXT")
	    private String comment;

	    private String image;
	    
	    @Column(nullable = false)
	    private Integer yummy;
	    
	    @Column(nullable = false)
	    private Integer clean;
	    
	    @Column(nullable = false)
	    private Integer staff;
	    
	    @Column(nullable = false)
	    private Integer speed;

//	    private String makeDate;
	    
	    
	    public Long getId() {
	        return id;
	    }
	    public void setId(Long id) {
	        this.id = id;
	    }
	    
	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }
	    
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
	    
	    
	    
	    public String getImage() {
	        return image;
	    }

	    public void setImage(String image) {
	        this.image = image;
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
	    

}
