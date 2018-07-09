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
import com.mapcamp.domain.repository.PostRepository;
import com.mapcamp.domain.repository.UserRepository;

public class CommentController {

	@Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/posts/{postId}/comment", method = RequestMethod.POST)
    ModelAndView createComment(@ModelAttribute Comment comment, @PathVariable Long postId,
            @AuthenticationPrincipal UserCustom userCustom, ModelAndView mav) {
        Post post = postRepository.findOne(postId);
        User user = userRepository.findOne(userCustom.getId());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.saveAndFlush(comment);
        mav.setViewName("redirect:/posts/" + postId);
        return mav;
    }
	
	
}
