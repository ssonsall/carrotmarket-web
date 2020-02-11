package com.bitc502.grapemarket.repository;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitc502.grapemarket.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>, JpaSpecificationExecutor<Board> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Board b set b.state = ?1, b.category = ?2, b.title = ?3, b.price = ?4, b.content = ?5, b.image1 = ?6, b.image2 = ?7, b.image3 = ?8, b.image4 = ?9, b.image5 = ?10 WHERE b.id = ?11")
	void update(String state, String cate, String title, String price, String content, String image1, String image2, String image3, String image4, String image5, int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Board b set b.state = ?1, b.buyerId = ?2 WHERE b.id = ?3", nativeQuery = true)
	void updateState(String state, int buyerId, int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Board b set b.state = ?1 WHERE b.id = ?2", nativeQuery = true)
	void updateTradeCancelState(String state, int id);

	// 검색용 쿼리
	Page<Board> findByCategory(String category, Pageable pageable);
	
	@Query(value = "SELECT * FROM Board b Join User u on b.userId = u.id WHERE category regexp :category AND concat(b.title, b.content, u.address) regexp :search", nativeQuery = true)
	Page<Board> findByCategoryAndUserInput(@Param("category") String cate, @Param("search") String search, Pageable pageable);

	@Query(value = "SELECT * FROM board b JOIN user u ON b.userid = u.id WHERE (addressX < ?1 and addressX > ?2 and addressY > ?3 and addressY < ?4) and state != -1", nativeQuery = true)
	Page<Board> findAllAndGps(double latitude, double latitude2, double longitude, double longitude2, Pageable pageable);

	@Query(value = "SELECT * FROM board b JOIN user u ON b.userid = u.id WHERE (addressX < ?1 and addressX > ?2 and addressY > ?3 and addressY < ?4) and category = ?5 and state != -1", nativeQuery = true)
	Page<Board> findByCategoryAndGps(double latitude, double latitude2, double longitude, double longitude2, String category, Pageable pageable);

	@Query(value = "SELECT * FROM board b Join user u on b.userId = u.id  WHERE category regexp ?5  AND concat(title, content, address) regexp ?6 AND addressX between ?1 and ?2 AND addressY between ?3 and ?4 and state != -1", nativeQuery = true)
	Page<Board> findByCategoryAndGpsAndUserInput(double latitude, double latitude2, double longitude, double longitude2, String cate,
			@Param("search") String search, Pageable pageable);

	// 인기게시물
	@Query(value = " SELECT * , (SELECT count(boardId) FROM comment WHERE createDate > (now()- INTERVAL 24 hour) AND boardId = b.id) AS commentCount, (SELECT count(boardId) FROM likes WHERE createDate > (now()- INTERVAL 24 hour) AND boardId = b.id) AS likesCount, (SELECT count(boardId) FROM chat WHERE createDate > (now()- INTERVAL 24 hour) AND boardId = b.id) AS chatCount FROM board AS b ORDER BY (likesCount * 0.2) +(commentCount * 0.5) +(chatCount*0.3) DESC LIMIT 10", nativeQuery = true)
	List<Board> popularBoard();

	// 통계
	@Query(value = "SELECT days.day AS date, COALESCE(t.cnt, 0) AS count FROM ( SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 day UNION SELECT CURDATE() - INTERVAL 2 day UNION SELECT CURDATE() - INTERVAL 3 day UNION SELECT CURDATE() - INTERVAL 4 day UNION SELECT CURDATE() - INTERVAL 5 day UNION SELECT CURDATE() - INTERVAL 6 day UNION SELECT CURDATE() - INTERVAL 7 day UNION SELECT CURDATE() - INTERVAL 8 day UNION SELECT CURDATE() - INTERVAL 9 day ) days LEFT JOIN ( SELECT DATE(createDate) AS date, COUNT(*) AS cnt FROM board WHERE createDate >= CURDATE() - INTERVAL 9 day GROUP BY DATE(createDate) ) t ON days.day = t.date ORDER BY date DESC", nativeQuery = true)
	List<Map<String, Object>> DealVolume();

	@Query(value = "SELECT days.day AS date, COALESCE(t.cnt, 0) AS count FROM ( SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 day UNION SELECT CURDATE() - INTERVAL 2 day UNION SELECT CURDATE() - INTERVAL 3 day UNION SELECT CURDATE() - INTERVAL 4 day UNION SELECT CURDATE() - INTERVAL 5 day UNION SELECT CURDATE() - INTERVAL 6 day UNION SELECT CURDATE() - INTERVAL 7 day UNION SELECT CURDATE() - INTERVAL 8 day UNION SELECT CURDATE() - INTERVAL 9 day ) days LEFT JOIN ( SELECT DATE(createDate) AS date, COUNT(*) AS cnt FROM board WHERE createDate >= CURDATE() - INTERVAL 9 day AND state =1 GROUP BY DATE(createDate) ) t ON days.day = t.date ORDER BY date DESC", nativeQuery = true)
	List<Map<String, Object>> completedDealVolume();

	List<Board> findTop3ByOrderByIdDesc();
	
	// 어드민
	List<Board> findByUserIdOrderByCreateDateDesc(int id);
	
	// 사용하지 않는 쿼리
//	int countFindByCategory(int category);
//	List<Board> findByCategory(String category);
//	Page<Board> findByState(String state, Pageable pageable);
//	Page<Board> findByStateAndCategory(String state, String category, Pageable pageable);
// 	Page<Board> findByTitleContainingOrContentContainingOrUserIdIn(String title, String content, List<Integer> userIds,Pageable page);
//	@Query(value = "SELECT * FROM Board b Join User u on b.userId = u.id WHERE category regexp :category AND concat(b.title, b.content, u.address) regexp :search", nativeQuery = true)
//	Page<Board> search(@Param("category") String cate, @Param("search") String search, Pageable page);
}