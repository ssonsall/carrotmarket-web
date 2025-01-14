package com.bitc502.grapemarket.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>, JpaSpecificationExecutor<Visitor> {

	// 통계
	@Query(value = "SELECT days.day AS date, COALESCE(t.cnt, 0) AS count FROM ( SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 day UNION SELECT CURDATE() - INTERVAL 2 day UNION SELECT CURDATE() - INTERVAL 3 day UNION SELECT CURDATE() - INTERVAL 4 day UNION SELECT CURDATE() - INTERVAL 5 day UNION SELECT CURDATE() - INTERVAL 6 day UNION SELECT CURDATE() - INTERVAL 7 day UNION SELECT CURDATE() - INTERVAL 8 day UNION SELECT CURDATE() - INTERVAL 9 day ) days LEFT JOIN ( SELECT DATE(createDate) AS date, COUNT(*) AS cnt FROM visitor WHERE createDate >= CURDATE() - INTERVAL 9 day GROUP BY DATE(createDate) ) t ON days.day = t.date ORDER BY date DESC;", nativeQuery = true)
	List<Map<String, Object>> visitorVolume();

}
