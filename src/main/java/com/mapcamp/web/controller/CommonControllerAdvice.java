package com.mapcamp.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mapcamp.security.LoginUserDetails;

//クラス内でのコントローラー共通処理は@ControllerAdvice
@ControllerAdvice
public class CommonControllerAdvice {

	//@AuthenticationPrincipalより,
    //ログインしているユーザの情報をLoginUserDetailsクラスとして引数で受け取る
	//@ModelAttribute：毎回、Requestmapingの前に呼ばれる(※①)
    @ModelAttribute(name = "loginUser")
    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        return loginUserDetails;
    }
	
}
