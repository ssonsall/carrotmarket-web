package com.bitc502.grapemarket.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Likes;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.LikeRepository;

@Service
public class LikeService {
	@Autowired
	private LikeRepository lRepo;

	public HashMap<String, Object> like(Likes like, User user) {
		like.setUser(user);
		HashMap<String, Object> statusMap = new HashMap<>();
		Likes check = lRepo.findByUserIdAndBoardId(user.getId(), like.getBoard().getId());
		if (check == null) {
			lRepo.save(like);
			int likeCount = lRepo.countByBoardId(like.getBoard().getId());

			statusMap.put("status", "save");
			statusMap.put("likeCount", likeCount);

			return statusMap;
		} else {
			lRepo.delete(check);
			int likeCount = lRepo.countByBoardId(like.getBoard().getId());

			statusMap.put("status", "delete");
			statusMap.put("likeCount", likeCount);

			return statusMap;
		}
	}

}
