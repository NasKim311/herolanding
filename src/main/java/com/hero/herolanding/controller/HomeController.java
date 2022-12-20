package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;
	
	@GetMapping
	public String index() {
		System.out.println("컨트롤러들어옴");
//		homeService.save();
//		homeService.saveCovidVaccin();
		
		return "index";
	}
	
//	@GetMapping("{search}")
//	public String search(@PathVariable String search, Model model) {
//		return "search";
//	}
	
//	임시
	
}
