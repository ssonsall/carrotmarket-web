package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	int countFindByCategory(int category);
	
	Page<Board> findByCategoryOrTitleContainingOrContentContainingOrUserIdIn(
			int id,String title, String content, List<Integer> userIds, Pageable page);
}
