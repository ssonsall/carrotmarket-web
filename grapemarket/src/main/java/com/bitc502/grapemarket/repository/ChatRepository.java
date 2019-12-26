package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	List<Chat> findByBuyerId(User buyerId);
	
	List<Chat> findBySellerId(User sellerId);
	
	Chat findById(int id);
	
	
	@Query(value = "SELECT * FROM chat WHERE boardId=?1 AND buyerId=?2 AND sellerId =?3",nativeQuery = true)
	Chat countByBoardIdAndBuyerIdAndSellerId(int board, int buyerId, int sellerId);
	
}
