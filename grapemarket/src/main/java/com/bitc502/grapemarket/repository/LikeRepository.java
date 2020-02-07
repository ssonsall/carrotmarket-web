package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

	Likes findByUserIdAndBoardId(int userId, int boardId);

	List<Likes> findByBoardId(int boardId);
	
	int countByBoardId(int boardId);
	
//	@Query(value = "SELECT * FROM likes WHERE userId=?1 AND boardId=?2", nativeQuery = true)
//	Likes findByUserIdAndBoardId(int userId, int boardId);
}
