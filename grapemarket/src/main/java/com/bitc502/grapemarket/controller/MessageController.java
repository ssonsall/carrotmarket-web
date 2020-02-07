package com.bitc502.grapemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.MessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MessageController {

	@Autowired
	private MessageService messageServ;

	private final SimpMessageSendingOperations messagingTemplate;

	@MessageMapping("/chat/message")
	public void message(Message message, @AuthenticationPrincipal UserPrincipal userPrincipal) {
		messagingTemplate.convertAndSend("/sub/chat/room/" + message.getTemp(), message);
		messageServ.setMessage(message);

	}
}
