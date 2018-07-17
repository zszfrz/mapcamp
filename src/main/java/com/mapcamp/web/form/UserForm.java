package com.mapcamp.web.form;

import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class UserForm {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 32) // 6文字以上32文字以下
	private String password;

	private String confirmPassword;

	@NotBlank
	private String name;

	//private String profileImage;
	private MultipartFile file;
	
	private Integer sex;

	private Date birthday;

	// ゲッターセッター
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 public String getConfirmPassword() {
	 return confirmPassword;
	 }
	
	 public void setConfirmPassword(String confirmPassword) {
	 this.confirmPassword = confirmPassword;
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getProfileImage() {
//		return profileImage;
//	}
//
//	public void setImage(String profileImage) {
//		this.profileImage = profileImage;
//	}
	
	public MultipartFile getFile() {
	    return file;
	}

	public void setFile(MultipartFile file) {
	    this.file = file;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}