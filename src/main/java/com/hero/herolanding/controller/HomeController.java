package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.repository.HomeRepository;
import com.hero.herolanding.service.HomeService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService ;
	
	@GetMapping
	public String home() {
//		homeService.save();
//		homeService.saveCovid();
//		homeService.saveCovidVaccin();
		return "index";
	}

}
