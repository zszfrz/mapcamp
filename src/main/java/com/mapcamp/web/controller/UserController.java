package com.mapcamp.web.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapcamp.domain.entity.User;
import com.mapcamp.domain.repository.UserRepository;
import com.mapcamp.domain.service.UserService;
import com.mapcamp.security.LoginUserDetails;
import com.mapcamp.web.form.UserForm;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	 // マイページの表示 表示させたいページのidを取得して表示(※①)
	 @GetMapping("/user/{id}")
	 public String show(@PathVariable Long id, Model model) {
     User user = userService.findOne(id);
	 model.addAttribute("user", user);
	 return "user/mypage";
	 }

    @GetMapping("/login")
    public String loginForm(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        if (loginUserDetails != null) {
            return "redirect:/";
        }
        return "user/login";
    }

	// 登録画面表示
	@GetMapping("/user/registration")
	public String signupForm(UserForm form, @AuthenticationPrincipal LoginUserDetails loginUserDetails) {
		if (loginUserDetails != null) {
			return "redirect:/";
		}
		return "/user/registration";
	}

	//登録画面でDBにデータ保存
	@PostMapping("/user/registration")
	public String register(@Validated UserForm form, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUserDetails loginUserDetails) throws IOException {
		//email,PW無い場合リダイレクト
		if (loginUserDetails != null) {
			return "redirect:/";
		}
		//パスワードが一致しない場合エラー表示
		if (!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("password", "error.passwordConfirmation", "do notmatch.");
		}
		
		if (result.hasErrors()) {
			return "/user/registration";
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		userService.save(user, form.getFile());
		return "redirect:/";

	}
	
	@GetMapping("/users/{id}/profile-image.jpg")
	@ResponseBody
	public byte[] downloadProfileImage(@PathVariable Long id) throws IOException {
	    return userService.downloadProfileImage(id);
	}
	
}
