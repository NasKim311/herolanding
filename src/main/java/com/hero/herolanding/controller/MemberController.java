package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	// 임시
	@GetMapping("/login")
		public String login() {
			System.out.println("로그인페이지");
		return "login/login";
		}

	// 임시
	@GetMapping("/join.html")
	public String admin() {
		System.out.println("회원가입페이지");
		return "login/join";
	}
}
