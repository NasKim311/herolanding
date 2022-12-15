package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

// 임시로 설정해놓은 경로(관리자페이지)
	@GetMapping("/admin")
	public String admin() {
		System.out.println("관리자페이지");
		return "heromanager/관리자페이지";
	}
	
// 회원관리.html
	@GetMapping("/회원관리.html")
	public String manager() {
		System.out.println("회원관리");
		return "heromanager/회원관리";
	}
	
// 게시판관리.html
	

}
