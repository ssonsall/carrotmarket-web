package com.bitc502.grapemarket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.bitc502.grapemarket.model.Chat;
import com.bitc502.grapemarket.model.Message;
import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.ChatRepository;
import com.bitc502.grapemarket.repository.MessageRepository;
import com.bitc502.grapemarket.repository.TradeStateRepository;
import com.bitc502.grapemarket.security.UserPrincipal;

import io.sentry.Sentry;

@Service
public class ChatService {

	@Autowired
	private TradeStateRepository tradeStateRepo;

	@Autowired
	private ChatRepository cRepo;

	@Autowired
	private MessageRepository messageRepo;

	// 채팅 페이지 접근시 채팅 목록 불러오기
	public Map<String, Object> Chat(UserPrincipal userPrincipal, Model model) {

		Map<String, Object> map = new HashMap<String, Object>();

		List<Chat> chatForBuy = cRepo.findByBuyerId(userPrincipal.getUser());
		List<Chat> chatForSell = cRepo.findBySellerId(userPrincipal.getUser());

		User user = userPrincipal.getUser();

		map.put("chatForBuy", chatForBuy);
		map.put("chatForSell", chatForSell);
		map.put("user", user);

		return map;
	}

	// 채팅방 생성
	public Chat CreateChat(Chat chat) {
		try {

			// 이미 생성된 구매목록이 있는지 확인
			int checkTradeState = tradeStateRepo.countByUserAndBoard(chat.getBuyerId(), chat.getBoard());
			if (checkTradeState == 0) {

				TradeState tradeState = new TradeState();
				tradeState.setUser(chat.getBuyerId());
				tradeState.setBoard(chat.getBoard());
				tradeState.setState("구매중");

				tradeStateRepo.save(tradeState);
			}

			// 생성된 채팅방이 있는지 확인
			Chat checkChateState = cRepo.findByBoardIdAndBuyerIdAndSellerId(chat.getBoard().getId(),
					chat.getBuyerId().getId(), chat.getSellerId().getId());

			// 채팅방이 있으면 활성화를 시키고 없으면 새로 생성
			if (checkChateState == null) {
				chat.setBuyerState(1);
				cRepo.save(chat);
				return chat;
			} else {
				checkChateState.setBuyerState(1);
				cRepo.save(checkChateState);
				return checkChateState;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}

		return null;
	}

	// 채팅 방 접속
	public List<Message> roomDetail(Model model, @PathVariable int roomId) {

		try {
			// 채팅방 지난 대화내역 가져오기
			List<Message> messages = messageRepo.findByChatIdOrderById(roomId);
			return messages;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}

		return null;
	}

	// 채팅방 찾기
	public Chat roomInfo(int roomId) {
		try {
			Chat chat = cRepo.findById(roomId);
			return chat;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}
		return null;
	}

	// 채팅 목록이 가지고 있는 아이템 정보 받아오기
	public Chat getProductInfo(int id) {

		try {
			Chat chat = cRepo.findById(id);
			return chat;
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}

		return null;
	}

	// 채팅방 나가기
	public void outChat(int id, String info) {

		try {
			Chat chat = cRepo.findById(id);

			if (info.equals("buyer")) {
				chat.setBuyerState(0);
			} else {
				chat.setSellerState(0);
			}

			cRepo.save(chat);
		} catch (Exception e) {
			e.printStackTrace();
			Sentry.capture(e);
		}

	}

	// 어드민
	public List<Message> chatLog(int id) {

		List<Message> chatLog = messageRepo.findByChatIdOrderByCreateDateDesc(id);
		return chatLog;

	}

}
