package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.User;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
	List<Chat> findByUser(User user);
}
