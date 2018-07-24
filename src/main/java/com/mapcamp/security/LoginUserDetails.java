package com.mapcamp.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.mapcamp.domain.entity.User;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User{

    private Long userId;
    private String profileImage;
    private String name;

    public LoginUserDetails(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userId = user.getId();
        this.profileImage = user.getProfileImage();
        this.name = user.getName();
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
    	return name;
    }
    
    public String getProfileImage() {
    	return profileImage;
    }

}
