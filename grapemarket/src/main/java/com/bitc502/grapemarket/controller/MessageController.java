package com.bitc502.grapemarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MessageController {

	@Autowired
	private MessageRepository mRepo;

	@Autowired
	private ChatRepository chatRepo;
	
	@Autowired
	private UserRepository uRepo;

	private final SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/chat/message")
	public void message(Message message, @AuthenticationPrincipal UserPrincipal userPrincipal) {

		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getTemp(), message);
		Chat chat = chatRepo.findById(message.getTemp());
		Optional<User> oUser = uRepo.findById(message.getTemp2());
		User user = oUser.get();
		message.setSenderId(user);
		message.setChat(chat);
		mRepo.save(message);

		if (chat.getBuyerState() == 0) {
			chat.setBuyerState(1);
		}

		if (chat.getSellerState() == 0) {
			chat.setSellerState(1);
		}

		chatRepo.save(chat);

	}
}
