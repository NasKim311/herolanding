package com.hero.herolanding.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.crawling.Cookies;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService ;
	private final Cookies cookiess;
	
	@GetMapping
	public String home(HttpServletResponse response ,HttpServletRequest request, Model model) {
		 Cookie[] cookies = request.getCookies(); 
		 if(cookies == null) {
			 cookiess.setCookie(response, request);
				homeService.save();
				homeService.saveCovid();
				homeService.saveCovidVaccin();
		}else {
			for(Cookie c : cookies) {
				if(!c.getName().equals("crawring")) {
					cookiess.setCookie(response, request);
					homeService.save();
					homeService.saveCovid();
					homeService.saveCovidVaccin();
				}
			}
		}
		 
//		homeService.worldMap();
		List<CovidDTO>  covids =  homeService.findCovid();
		model.addAttribute("Covids" , covids);
		return "index";
	}
	
	@Transactional
	@GetMapping("/worldMap")
	public void worldMap() {
		
		// json 파일로 만들어주기
	}

}
