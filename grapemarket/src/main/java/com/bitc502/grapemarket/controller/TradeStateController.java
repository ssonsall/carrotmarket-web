package com.bitc502.grapemarket.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bitc502.grapemarket.model.AuthProvider;
import com.bitc502.grapemarket.model.Role;
import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.repository.UserRepository;
import com.bitc502.grapemarket.security.UserPrincipal;
import com.bitc502.grapemarket.service.TradeStateService;
import com.bitc502.grapemarket.util.Script;

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

	
}
