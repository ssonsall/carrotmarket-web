package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Likes;

public interface LikeRepository extends JpaRepository<Likes, Integer> {

	Likes findByUserIdAndBoardId(int userId, int boardId);

	int countByBoardId(int boardId);
	
	// 사용하지 않는 쿼리
//	List<Likes> findByBoardId(int boardId);
//	@Query(value = "SELECT * FROM likes WHERE userId=?1 AND boardId=?2", nativeQuery = true)
//	Likes findByUserIdAndBoardId(int userId, int boardId);
}
