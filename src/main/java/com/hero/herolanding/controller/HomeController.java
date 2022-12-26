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
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.crawling.Cookies;
import com.hero.herolanding.domain.CountryPaper;
import com.hero.herolanding.domain.Inspection;
import com.hero.herolanding.dto.CovidDTO;
import com.hero.herolanding.dto.CovidOneDTO;
import com.hero.herolanding.dto.NewsDTO;
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
	
	// 메인페이지
	@GetMapping
	public String home(HttpServletResponse response ,HttpServletRequest request, Model model) {
		 Cookie[] cookies = request.getCookies(); 
		 int cnt = 0 ;
		 if(cookies == null) {
			 	cookiess.setCookie(response, request);
				homeService.save();
				homeService.saveCovid();
				homeService.saveCovidVaccin();
//				try {
//					homeService.coivdMap();
//				} catch (FileNotFoundException e) {
//					System.out.println("파일경로에러");
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
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
//						try {
//							homeService.coivdMap();
//						} catch (FileNotFoundException e) {
//							System.out.println("파일경로에러");
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				}
			}
		}
//			try {
//				homeService.coivdMap();
//				System.out.println("새끼 나와라");
//			} catch (FileNotFoundException e) {
//				System.out.println("파일경로에러");
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
		List<CovidDTO>  covids =  homeService.findCovid();
		List<vaccinDTO> vaccins = homeService.findCovidVaccin();
		CovidOneDTO oneDTO = homeService.findCoivdCounrty("전세계");
		List<NewsDTO> newsDTOs = homeService.findNews("코로나");
		
		model.addAttribute("news",newsDTOs);
		model.addAttribute("one",oneDTO);
		model.addAttribute("Covids" , covids);
		model.addAttribute("vaccins" , vaccins);
		return "index";
	}
	
	// 검색 후 페이지
	@Transactional
	@GetMapping("/search")
	public String worldMap(@RequestParam String country , Model model) {
		
		List<CountryPaper> countryPapers = homeService.findCountryPaper(country);
		if(countryPapers.size() == 0) {
			countryPapers.add(new CountryPaper()) ;
		}
		List<Inspection> inspections = homeService.findInspection(country);
		if(inspections.size() == 0 ) {
			inspections.add(new Inspection());
		}
		// 나라정보0
		model.addAttribute("country" , homeService.findCountry(country));
		// 뉴스정보0
		model.addAttribute("news" , homeService.findNews(country));
		// 코로나 정보0
		model.addAttribute("covid" , homeService.findCoivdCounrty(country));
		// 환율 정보0
		model.addAttribute("ex" , homeService.exchangeOne(country));
		// 서류 정보0
		model.addAttribute("countryPaper" ,countryPapers );
		// 검사 정보
		model.addAttribute("inspection" ,inspections);
		// 비자 정보0
		model.addAttribute("visa",homeService.findVisa(country));
		return "result/result";
	}

}
