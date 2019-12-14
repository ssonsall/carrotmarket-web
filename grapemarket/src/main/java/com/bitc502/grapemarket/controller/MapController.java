package com.bitc502.grapemarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {

	@GetMapping("/sample")
	public String sample() {
		return "map/Sample";
	}
	@GetMapping("/popup")
	public String popup1() {
		return "map/jusoPopup";
	}
	@PostMapping("/popup")
	public String popup2() {
		return "map/jusoPopup";
	}
	
}
