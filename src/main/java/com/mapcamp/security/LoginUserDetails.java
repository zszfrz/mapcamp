package com.mapcamp.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.mapcamp.domain.entity.User;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User{

    private Long userId;

    public LoginUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }

}
