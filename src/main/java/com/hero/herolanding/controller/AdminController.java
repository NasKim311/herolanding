package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

// 임시로 설정해놓은 경로(관리자페이지)
	@GetMapping("/admin")
	public String admin() {
		System.out.println("관리자페이지");
		return "admin/관리자페이지";
	}
	
// 회원관리.html
	@GetMapping("/회원관리")
	public String manager() {
		System.out.println("회원관리");
		return "admin/회원관리";
	}
	
// 게시글관리.html
	@GetMapping("/게시글관리")
	public String board() {
		System.out.println("게시글관리");
		return "admin/게시글관리";
	}

// 신고글관리.html
	@GetMapping("/신고글관리")
	public String report() {
		System.out.println("신고글관리");
		return "admin/신고글관리";
	}

// 회원정보수정.html
	@GetMapping("/회원정보수정")
	public String member() {
		System.out.println("회원정보수정");
		return "admin/회원정보수정";
	}

// DB관리.html
	@GetMapping("/DB관리")
	public String db() {
		System.out.println("DB관리");
		return "admin/DB관리";
	}

// DB수정.html
	@GetMapping("/DB수정")
	public String dbupdate() {
		System.out.println("DB수정");
		return "admin/DB수정";
	}

}
