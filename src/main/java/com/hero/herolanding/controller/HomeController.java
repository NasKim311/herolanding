package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hero.herolanding.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("{search}")
	public String search(@PathVariable String search, Model model) {
		return "search";
	}
	
	
}
