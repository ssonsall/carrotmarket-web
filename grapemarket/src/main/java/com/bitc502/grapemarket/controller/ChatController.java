package com.bitc502.grapemarket.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.BoardRepository;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.TradeStateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatRepository cRepo;

	@Autowired
	private MessageRepository mRepo;

	@Autowired
	private BoardRepository bRepo;
	
	@Autowired
	private TradeStateService tradeStateServ;

	@PostMapping("/chat")
	public @ResponseBody String CreateChat(Chat chat) {

		tradeStateServ.insertBuyState(chat.getBuyerId(), chat.getBoard());
		
		try {
			Chat check = cRepo.findByBoardIdAndBuyerIdAndSellerId(chat.getBoard().getId(), chat.getBuyerId().getId(),
					chat.getSellerId().getId());

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


	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable int roomId) {
		model.addAttribute("roomId", roomId);
		List<Message> messages = mRepo.findByChatIdOrderByIdDesc(roomId);
		model.addAttribute("messages", messages);
		return "chat/roomdetail";
	}

	@GetMapping("/room/{roomId}")
	public Chat roomInfo(@PathVariable int roomId) {
		Chat chat = cRepo.findById(roomId);
		return chat;
	}

	@GetMapping("/product/{id}")
	public @ResponseBody Chat JsonTest(@PathVariable int id) {

		Chat chat = cRepo.findById(id);

		return chat;

	}

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
