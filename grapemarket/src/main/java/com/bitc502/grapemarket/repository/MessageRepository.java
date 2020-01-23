package com.bitc502.grapemarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitc502.grapemarket.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{

	List<Message> findByChatIdOrderByIdDesc(int id);

	List<Message> findByChatIdOrderById(int roomId);

	

}
