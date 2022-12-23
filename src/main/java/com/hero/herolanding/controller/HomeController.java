package com.hero.herolanding.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.crawling.Cookies;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.dto.CovidOneDTO;
import com.hero.herolanding.dto.vaccinDTO;
import com.hero.herolanding.service.HomeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	@Autowired
	private final HomeService homeService ;
	@Autowired
	private final Cookies cookiess;
	
	@GetMapping
	public String home(HttpServletResponse response ,HttpServletRequest request, Model model) {
		 Cookie[] cookies = request.getCookies(); 
		 int cnt = 0 ;
		 if(cookies == null) {
			 	cookiess.setCookie(response, request);
				homeService.save();
				homeService.saveCovid();
				homeService.saveCovidVaccin();
				try {
					homeService.coivdMap();
				} catch (FileNotFoundException e) {
					System.out.println("파일경로에러");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else {
			for(Cookie c : cookies) {
				if(c.getName().equals("crawring")) {
					cnt++;
					if(cnt == 0) {
						System.out.println("크롤링아닌 쿠키값있을떄");
						cookiess.setCookie(response, request);
						homeService.save();
						homeService.saveCovid();
						homeService.saveCovidVaccin();
						try {
							homeService.coivdMap();
						} catch (FileNotFoundException e) {
							System.out.println("파일경로에러");
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
			try {
				homeService.coivdMap();
				System.out.println("새끼 나와라");
			} catch (FileNotFoundException e) {
				System.out.println("파일경로에러");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		List<CovidDTO>  covids =  homeService.findCovid();
		List<vaccinDTO> vaccins = homeService.findCovidVaccin();
		CovidOneDTO oneDTO = homeService.findCounrty("전세계");
		
		model.addAttribute("one",oneDTO);
		model.addAttribute("Covids" , covids);
		model.addAttribute("vaccins" , vaccins);
		return "index";
	}
	
	@Transactional
	@GetMapping("/search")
	public String worldMap() {
		return "result/result";
	}

}
