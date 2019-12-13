package com.bitc502.grapemarket.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bitc502.grapemarket.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);
	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.password=?1, u.email = ?2, u.phone = ?3 WHERE u.id = ?4")
	void update(String password, String email, String phone, int id);
}
