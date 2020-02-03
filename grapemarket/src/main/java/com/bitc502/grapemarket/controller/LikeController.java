package com.bitc502.grapemarket.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Likes;
import com.bitc502.grapemarket.repository.LikeRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.LikeService;

@Controller
@RequestMapping("like")
public class LikeController {

	@Autowired
	private LikeService likeServ;

	@PostMapping("/like")
	public @ResponseBody HashMap<String, Object> Like(Likes like,
			@AuthenticationPrincipal UserPrincipal userPrincipal) {
		return likeServ.like(like, userPrincipal.getUser());
	}

}
