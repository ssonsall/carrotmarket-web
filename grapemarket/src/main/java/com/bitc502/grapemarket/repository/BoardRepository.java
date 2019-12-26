package com.bitc502.grapemarket.repository;

import java.util.List;

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
	int countFindByCategory(int category);

	@Query(value = "SELECT * FROM Board b Join User u on b.userId = u.id WHERE category regexp :category AND concat(b.title, b.content, u.address) regexp :search", nativeQuery = true)
	Page<Board> search(@Param("category") String cate, @Param("search") String search, Pageable page);

	Page<Board> findByTitleContainingOrContentContainingOrUserIdIn(String title, String content, List<Integer> userIds,
			Pageable page);

	Page<Board> findByCategory(String category, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Board b set b.state = ?1, b.category = ?2, b.title = ?3, b.price = ?4, b.content = ?5, b.image1 = ?6, b.image2 = ?7, b.image3 = ?8, b.image4 = ?9, b.image5 = ?10 WHERE b.id = ?11")
	void update(String state, int category, String title, String price, String content, String image1, String image2,
			String image3, String image4, String image5, int id);

	Page<Board> findByState(String state, Pageable pageable);

	Page<Board> findByStateAndCategory(String state, String category, Pageable pageable);
	
	
}