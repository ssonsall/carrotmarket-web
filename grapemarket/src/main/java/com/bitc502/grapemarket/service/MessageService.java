package com.bitc502.grapemarket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private MessageRepository mRepo;
	
	@Autowired
	private ChatRepository cRepo;

	public void setMessage(Message message) {

		try {
			Chat chat = cRepo.findById(message.getTemp());
			Optional<User> oUser = uRepo.findById(message.getTemp2());
			User user = oUser.get();
			message.setUser(user);
			message.setChat(chat);
			mRepo.save(message);
			if (chat.getBuyerState() == 0) {
				chat.setBuyerState(1);
			}
			if (chat.getSellerState() == 0) {
				chat.setSellerState(1);
			}
			cRepo.save(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
