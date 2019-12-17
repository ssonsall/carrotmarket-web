package com.bitc502.grapemarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MessageController {

	@Autowired
	private MessageRepository mRepo;
	
	
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(Message message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    	
    	
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getTemp(), message);
        
        Chat chat = new Chat();
        chat.setId(message.getTemp());
        message.setChat(chat);
    	System.out.println(message);
    	mRepo.save(message);
    
    	
    }
}
