package com.bitc502.grapemarket.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);

	Boolean existsByUsername(String username);
	

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.password=?1, u.email = ?2, u.phone = ?3, u.userProfile = ?4 WHERE u.id = ?5")
	void update(String password, String email, String phone, String userProfile, int id);

	List<User> findByAddressContaining(String address);

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.address=?1, u.addressX = ?2, u.addressY = ?3, u.addressAuth=0 WHERE u.id = ?4")
	void addUpdate(String address, Double addressX, Double addressY, int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.addressAuth=1 WHERE u.id = ?1")
	void authUpdate(int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.password=?1 WHERE u.id = ?2")
	void androidPasswordUpdate(String password, int id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.email = ?1, u.phone = ?2, u.userProfile = ?3 WHERE u.id = ?4")
	void androidUpdate(String email, String phone, String userProfile, int id);

	@Query(value = "SELECT days.day AS date, COALESCE(t.cnt, 0) AS count FROM ( SELECT CURDATE() AS day UNION SELECT CURDATE() - INTERVAL 1 day UNION SELECT CURDATE() - INTERVAL 2 day UNION SELECT CURDATE() - INTERVAL 3 day UNION SELECT CURDATE() - INTERVAL 4 day UNION SELECT CURDATE() - INTERVAL 5 day UNION SELECT CURDATE() - INTERVAL 6 day UNION SELECT CURDATE() - INTERVAL 7 day UNION SELECT CURDATE() - INTERVAL 8 day UNION SELECT CURDATE() - INTERVAL 9 day ) days LEFT JOIN ( SELECT DATE(createDate) AS date, COUNT(*) AS cnt FROM user WHERE createDate >= CURDATE() - INTERVAL 9 day GROUP BY DATE(createDate) ) t ON days.day = t.date ORDER BY date DESC;", nativeQuery = true)
	List<Map<String, Object>> memberVolume();

	@Query(value = "SELECT * from user WHERE addressX < ?1 and addressX > ?2 and addressY > ?3 and addressY < ?4", nativeQuery = true)
	List<User> findByGPS(double nwLatitude, double seLatitude, double nwLongitude, double seLongitude);
}
