package com.mapcamp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mapcamp.domain.entity.Comment;
import com.mapcamp.domain.entity.Post;
import com.mapcamp.domain.entity.User;
import com.mapcamp.domain.repository.CommentRepository;
import com.mapcamp.domain.service.PostService;
import com.mapcamp.domain.service.UserService;
import com.mapcamp.security.LoginUserDetails;

public class CommentController {

	@Autowired
    CommentRepository commentRepository;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;
    @ModelAttribute(name = "loginUser")
    private LoginUserDetails setupLoginUser(@AuthenticationPrincipal LoginUserDetails loginUserDetails) {
        return loginUserDetails;
    }
   

    @RequestMapping(value = "/posts/{postId}/comment", method = RequestMethod.POST)
    ModelAndView createComment(@ModelAttribute Comment comment, @PathVariable Long postId,
            @AuthenticationPrincipal LoginUserDetails loginUserDetails, ModelAndView mav) {
        Post post = postService.findOne(postId);
        User user = userService.findOne(loginUserDetails.getUserId());
        //User user = userService.findOne(loginUserDetails.);
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.saveAndFlush(comment);
        mav.setViewName("redirect:/posts/" + postId);
        return mav;
    }
	
	
}
