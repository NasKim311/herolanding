package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {


	// 임시
	@GetMapping("/join.html")
	public String admin() {
		System.out.println("회원가입페이지");
		return "login/join";
	}
}
