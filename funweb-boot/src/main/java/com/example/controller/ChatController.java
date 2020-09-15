package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.java.Log;

@Log
@RequestMapping("/chat/*")
@Controller
public class ChatController {
	@GetMapping("/chat")
	public String chat() {
		log.info("chat.............");
		return "chat/chat";
	}

}
