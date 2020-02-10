package com.bitc502.grapemarket.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bitc502.grapemarket.model.User;
import com.bitc502.grapemarket.payload.ApiResponse;
import com.bitc502.grapemarket.payload.SignUpRequest;
import com.bitc502.grapemarket.service.OAuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private OAuthService oAuthServ;

	// postman
	@PostMapping("/signup")
	public @ResponseBody ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		User result = oAuthServ.setUser(signUpRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(result.getId()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
	}

}