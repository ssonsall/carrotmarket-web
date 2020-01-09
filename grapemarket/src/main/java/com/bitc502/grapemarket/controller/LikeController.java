package com.bitc502.grapemarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Likes;
import com.bitc502.grapemarket.repository.LikeRepository;
import com.bitc502.grapemarket.security.UserPrincipal;

@Controller
@RequestMapping("like")
public class LikeController {

	@Autowired
	private LikeRepository likeRepository;

	@PostMapping("/like")
	public @ResponseBody HashMap<String, Object> Like(Likes like, @AuthenticationPrincipal UserPrincipal userPrincipal) {

		like.setUser(userPrincipal.getUser());
		
		HashMap<String, Object> statusMap = new HashMap<>();
		Likes check = likeRepository.findByUserIdAndBoardId(userPrincipal.getUser().getId(), like.getBoard().getId());
		
		if (check == null) {
			likeRepository.save(like);
			int likeCount = likeRepository.countByBoardId(like.getBoard().getId());
			statusMap.put("status", "save");
			statusMap.put("likeCount", likeCount);

			return statusMap;
		} else {
			likeRepository.delete(check);
			int likeCount = likeRepository.countByBoardId(like.getBoard().getId());
			statusMap.put("status", "delete");
			statusMap.put("likeCount", likeCount);
			
			return statusMap;
		}

	}

}
