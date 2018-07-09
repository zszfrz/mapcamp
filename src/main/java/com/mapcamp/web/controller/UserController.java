package com.mapcamp.web.controller;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mapcamp.domain.entity.User;
import com.mapcamp.domain.repository.UserRepository;
import com.mapcamp.domain.service.UserService;
import com.mapcamp.security.LoginUserDetails;
import com.mapcamp.web.form.UserForm;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	// @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	// public ModelAndView registrarion(@ModelAttribute("user") User user,
	// ModelAndView mav) {
	// userRepository.save(user);
	// mav.setViewName("/user/registration");//"redirect:/user/login"
	// return mav;
	// }

	// @GetMapping("/login")
	// public String loginForm(@AuthenticationPrincipal LoginUserDetails
	// loginUserDetails) {
	// if (loginUserDetails != null) {
	// return "redirect:/";
	// }
	// return "user/login";
	// }

	// // マイページの表示
	// @GetMapping("/users/{id}")
	// public String show(@PathVariable Long id, Model model) {
	// User user = UserService.findOne(id);
	// model.addAttribute("user", user);
	// return "users/show";
	// }

    @GetMapping("/login")
    public String loginForm(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        if (loginUserDetails != null) {
            return "redirect:/";
        }
        return "user/login";
    }

	// 登録画面表示
	@GetMapping("/user/registration")
	public String signupForm(UserForm form, @AuthenticationPrincipal LoginUserDetails loginUserDtails) {
		if (loginUserDtails != null) {
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
		//
		if (result.hasErrors()) {
			return "/user/registration";
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);

		//userService.save(user, form.getFile());
		 userService.save(user);
		return "redirect:/";

	}
}
