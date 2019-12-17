package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	int countFindByCategory(int category);
	
	Page<Board> findByCategoryAndTitleContainingOrCategoryAndContentContainingOrCategoryAndUserIdIn(
			int id,String title, int id2, String content, int id3,List<Integer> userIds, Pageable page);
	
	Page<Board> findByCategory(int id, Pageable page);
	
	Page<Board> findByTitleContainingOrContentContainingOrUserIdIn(
			String title, String content, List<Integer> userIds, Pageable page);
	
}
