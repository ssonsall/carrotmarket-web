package com.bitc502.grapemarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc502.grapemarket.model.TradeState;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.TradeStateService;

@Controller
@RequestMapping("/tradeState")
public class TradeStateController {

	@Autowired
	private TradeStateService tradeStateServ;

	
	//거래 상태 확인
	@PostMapping("/checkState")
	public @ResponseBody String checkState(@RequestBody String json) {
		String state = tradeStateServ.checkState(json);
		System.out.println("state : "+state);
		return state;
	}
	
	
	//구매완료버튼 입력
	@PostMapping("/setStateComplete")
	public @ResponseBody String setTradeStateComplete(@RequestBody String json) {

		tradeStateServ.setStateComplete(json);

		return "ok";
	}

	//trade리스트 가져오기
	@GetMapping("/tradeList")
	public @ResponseBody List<TradeState> getState(@AuthenticationPrincipal UserPrincipal userPrincipal){
		
		int userId = userPrincipal.getUser().getId();
		
		List<TradeState> tradeStates = tradeStateServ.getListByUserId(userId);
		
		return tradeStates;
	}
	
	
}
