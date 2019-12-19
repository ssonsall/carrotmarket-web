package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bitc502.grapemarket.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>, JpaSpecificationExecutor<Board> {
   int countFindByCategory(int category);

   @Query(value = "SELECT * FROM Board b Join User u on b.userId = u.id WHERE category regexp :category AND concat(b.title, b.content, u.address) regexp :search", nativeQuery = true)
   Page<Board> search(@Param("category") String cate, @Param("search") String search, Pageable page);
   
   Page<Board> findByCategory(int id, Pageable page);

   Page<Board> findByTitleContainingOrContentContainingOrUserIdIn(String title, String content, List<Integer> userIds,
         Pageable page);

}