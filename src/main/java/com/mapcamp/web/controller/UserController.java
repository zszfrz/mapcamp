package com.mapcamp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mapcamp.security.LoginUserDetails;

@Controller
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String loginForm(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        if (loginUserDetails != null) {
            return "redirect:/";
        }
        return "user/login";
    }
}
