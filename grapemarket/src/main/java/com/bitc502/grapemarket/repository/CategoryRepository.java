package com.bitc502.grapemarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
