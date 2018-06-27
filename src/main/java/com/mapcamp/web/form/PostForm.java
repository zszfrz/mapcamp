package com.mapcamp.web.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostForm {
	
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


	
}
