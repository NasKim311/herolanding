package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

//--------<mypageForm() / 마이 페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/mypageForm")
	public String mypageForm() {
		return "mypage/마이페이지";
	}
	
} // MypageController class
