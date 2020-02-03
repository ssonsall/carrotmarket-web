package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Search;

public interface SearchRepository extends JpaRepository<Search, Integer>{

	@Query(value = "SELECT * FROM search WHERE createDate > (now()- INTERVAL 3 hour) GROUP BY content ORDER BY count(content) DESC limit 7", nativeQuery = true)
	List<Search> popularKeyword();
	
	@Query(value = "SELECT * FROM search GROUP BY content ORDER BY count(content) DESC limit 3", nativeQuery = true)
	List<Search> wholeTimePopularThreeKeyword();
}
