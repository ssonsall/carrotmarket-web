package com.bitc502.grapemarket.controller;

import java.util.List;

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
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.TradeStateService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatRepository cRepo;

	@Autowired
	private MessageRepository mRepo;
	
	@Autowired
	private TradeStateService tradeStateServ;

	@PostMapping("/chat")
	public @ResponseBody String CreateChat(Chat chat) {

		tradeStateServ.insertBuyState(chat.getBuyerId(), chat.getBoard());
		
		try {
			Chat check = cRepo.findByBoardIdAndBuyerIdAndSellerId(chat.getBoard().getId(), chat.getBuyerId().getId(),
					chat.getSellerId().getId());

			// 채팅방에 메시지 전송시 상대방의 채팅방이 활성화 되어있지 않은 상태라면 활성화
			if (check == null) {
				chat.setBuyerState(1);
				cRepo.save(chat);
			} else {
				check.setBuyerState(1);
				cRepo.save(check);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "ok";
	}

	
	//채팅 페이지
	@GetMapping("/")
	public String Chat(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

		List<Chat> chatForBuy = cRepo.findByBuyerId(userPrincipal.getUser());
		List<Chat> chatForSell = cRepo.findBySellerId(userPrincipal.getUser());

		User user = userPrincipal.getUser();

		model.addAttribute("user", user);
		model.addAttribute("chatForBuy", chatForBuy);
		model.addAttribute("chatForSell", chatForSell);

		return "chat/chat";
	}


	//채팅 방 접속
	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable int roomId) {
		model.addAttribute("roomId", roomId);
		List<Message> messages = mRepo.findByChatIdOrderById(roomId);
		model.addAttribute("messages", messages);
		return "chat/roomdetail";
	}

	//
	@GetMapping("/room/{roomId}")
	public Chat roomInfo(@PathVariable int roomId) {
		Chat chat = cRepo.findById(roomId);
		return chat;
	}

	
	//채팅 목록이 가지고 있는 아이템 정보 받아오기
	@GetMapping("/product/{id}")
	public @ResponseBody Chat JsonTest(@PathVariable int id) {

		Chat chat = cRepo.findById(id);

		return chat;

	}

	
	//채팅방 나가기
	@GetMapping("/outChat/{id}/{info}")
	public @ResponseBody void outChat(@PathVariable int id, @PathVariable String info) {

		Chat chat = cRepo.findById(id);

		if (info.equals("buyer")) {
			chat.setBuyerState(0);
		} else {
			chat.setSellerState(0);
		}

		cRepo.save(chat);
	}
	

	
	
}
