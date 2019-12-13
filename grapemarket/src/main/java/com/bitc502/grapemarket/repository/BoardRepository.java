package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	int countFindByCategory(int category);
}
