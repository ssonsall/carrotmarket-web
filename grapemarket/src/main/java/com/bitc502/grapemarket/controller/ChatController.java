package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Board;
import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.security.MyUserDetails;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatRepository cRepo;
	
	@Autowired
	private MessageRepository mRepo;
	
	@Autowired
	private BoardRepository bRepo;

	@PostMapping("/chat")
	public @ResponseBody String CreateChat(Chat chat) {

		cRepo.save(chat);
		return "ok";
	}

	@GetMapping("/")
	public String Chat(@AuthenticationPrincipal MyUserDetails userDetail, Model model) {

		List<Chat> chatForBuy = cRepo.findByBuyerId(userDetail.getUser());
		List<Chat> chatForSell = cRepo.findBySellerId(userDetail.getUser());
//				userDetail.getUser().getId()

		User user = userDetail.getUser();
		
		model.addAttribute("user",user);
		model.addAttribute("chatForBuy", chatForBuy);
		model.addAttribute("chatForSell", chatForSell);

		return "chat/chat";
	}

//	@PostMapping("/room")
//    @ResponseBody
//    public ChatRoom createRoom(@RequestParam String name) {
//        return chatRoomRepository.createChatRoom(name); //룸생성
//    }

	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable int roomId) {
		model.addAttribute("roomId", roomId);
		List<Message> messages = mRepo.findByChatIdOrderByIdDesc(roomId);
		model.addAttribute("messages",messages);
		return "chat/roomdetail";
	}


	@GetMapping("/room/{roomId}")
	public Chat roomInfo(@PathVariable int roomId) {
		Optional<com.bitc502.grapemarket.model.Chat> oChat = cRepo.findById(roomId);
		Chat chat = oChat.get();
		return chat;
	}
	
	
	@GetMapping("/test/{id}")
	public @ResponseBody Chat JsonTest(@PathVariable int id) {
		
		Optional<com.bitc502.grapemarket.model.Chat> oChat = cRepo.findById(id);
		Chat chat = oChat.get();
		
		return chat;
		
	}
	
}
