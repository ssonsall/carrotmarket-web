package com.bitc502.grapemarket.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	
	Chat findById(int id);	
	
	List<Chat> findByBuyerId(User buyerId);
	
	List<Chat> findBySellerId(User sellerId);
	
	List<Chat> findByBuyerIdAndBuyerState(User user, int i);

	List<Chat> findBySellerIdAndSellerState(User user, int i);
	
	@Query(value = "SELECT * FROM chat WHERE boardId=?1 AND buyerId=?2 AND sellerId =?3",nativeQuery = true)
	Chat findByBoardIdAndBuyerIdAndSellerId(int board, int buyerId, int sellerId);

	//통계
	@Query(value = "SELECT days.day AS date, COALESCE(t.cnt, 0) AS count FROM ( SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 day UNION SELECT CURDATE() - INTERVAL 2 day UNION SELECT CURDATE() - INTERVAL 3 day UNION SELECT CURDATE() - INTERVAL 4 day UNION SELECT CURDATE() - INTERVAL 5 day UNION SELECT CURDATE() - INTERVAL 6 day UNION SELECT CURDATE() - INTERVAL 7 day UNION SELECT CURDATE() - INTERVAL 8 day UNION SELECT CURDATE() - INTERVAL 9 day ) days LEFT JOIN ( SELECT DATE(createDate) AS date, COUNT(*) AS cnt FROM chat WHERE createDate >= CURDATE() - INTERVAL 9 day GROUP BY DATE(createDate) ) t ON days.day = t.date ORDER BY date DESC;", nativeQuery = true)
	List<Map<String, Object>> chatVolume();
	
	// 사용하지 않는 쿼리
//	Chat findByBoardIdAndBuyerId(int boardId, int buyerId);


}
