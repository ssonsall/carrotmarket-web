package com.bitc502.grapemarket.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {


	@Autowired
	private MessageRepository mRepo;

	@Autowired
	private ChatService chatServ;

	@PostMapping("/chat")
	public @ResponseBody Chat CreateChat(Chat chat) {

		Chat foundchat = chatServ.CreateChat(chat);

		return foundchat;
	}

	// 채팅 페이지 접근시 채팅 목록 불러오기
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public String Chat(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

		Map<String, Object> map = chatServ.Chat(userPrincipal, model);

		User user = (User) map.get("user");
		List<Chat> chatForBuy = (List<Chat>) map.get("chatForBuy");
		List<Chat> chatForSell = (List<Chat>) map.get("chatForSell");

		model.addAttribute("user", user);
		model.addAttribute("chatForBuy", chatForBuy);
		model.addAttribute("chatForSell", chatForSell);

		return "chat/chat";
	}

	// 채팅 방 접속
	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable int roomId) {

		// 채팅방 지난 대화내역 가져오기
		List<Message> messages = chatServ.roomDetail(model, roomId);

		model.addAttribute("roomId", roomId);
		model.addAttribute("messages", messages);

		return "chat/roomdetail";
	}

	// 채팅방 찾기
	@GetMapping("/room/{roomId}")
	public Chat roomInfo(@PathVariable int roomId) {
		Chat chat = chatServ.roomInfo(roomId);
		return chat;
	}

	// 채팅 목록이 가지고 있는 아이템 정보 받아오기
	@GetMapping("/product/{id}")
	public @ResponseBody Chat getProductInfo(@PathVariable int id) {

		Chat chat = chatServ.getProductInfo(id);
		return chat;
	}

	// 채팅방 나가기
	@GetMapping("/outChat/{id}/{info}")
	public @ResponseBody void outChat(@PathVariable int id, @PathVariable String info) {

		// 아이디 값을 찾아서 채팅방 접속상태를 1(접속중)에서 0(채팅방종료)으로 변경
		chatServ.outChat(id, info);

	}

	@GetMapping("/chatLog/{id}")
	public @ResponseBody List<Message> chatLog(@PathVariable int id) {
		List<Message> chatLog = mRepo.findByChatIdOrderByCreateDateDesc(id);
		return chatLog;

	}

}
