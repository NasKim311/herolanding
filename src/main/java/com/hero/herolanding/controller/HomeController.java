package com.hero.herolanding.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.crawling.Cookies;
import com.hero.herolanding.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService ;
	private final Cookies cookiess;
	
	@GetMapping
	public String home(HttpServletResponse response ,HttpServletRequest request) {
		 Cookie[] cookies = request.getCookies(); 
		 if(cookies == null) {
			 cookiess.setCookie(response, request);
				homeService.save();
//				homeService.saveCovid();
//				homeService.saveCovidVaccin();
		}else {
			for(Cookie c : cookies) {
				if(!c.getName().equals("crawring")) {
					cookiess.setCookie(response, request);
					homeService.save();
//					homeService.saveCovid();
//					homeService.saveCovidVaccin();
				}
			}
		}
		 
		homeService.worldMap();
		return "index";
	}

}
