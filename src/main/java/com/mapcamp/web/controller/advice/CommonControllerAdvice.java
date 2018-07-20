package com.mapcamp.web.controller.advice;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mapcamp.security.LoginUserDetails;

public class CommonControllerAdvice {
	
	@ModelAttribute(name = "loginUser")
	private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
	    return loginUserDetails;
	}
}
