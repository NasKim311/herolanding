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
	
// 마이페이지.html 프론트 작업용 임시
	@GetMapping("/마이페이지")
	public String 마이페이지() {
		System.out.println("마이페이지");
		return "mypage/마이페이지";
	}

// 내가작성한게시글페이지.html 프론트 작업용 임시
	@GetMapping("/내가작성한게시글페이지")
	public String 내가작성한게시글페이지() {
		System.out.println("내가작성한게시글페이지");
		return "mypage/내가작성한게시글페이지";
	}

// 내가작성한댓글목록리스트.html 프론트 작업용 임시
	@GetMapping("/내가작성한댓글목록리스트")
	public String 내가작성한댓글목록리스트() {
		System.out.println("내가작성한댓글목록리스트");
		return "mypage/내가작성한댓글목록리스트";
	}

// 등록한여행지의정보를보여주는페이지.html 프론트 작업용 임시
	@GetMapping("/등록한여행지의정보를보여주는페이지")
	public String 등록한여행지의정보를보여주는페이지() {
		System.out.println("등록한여행지의정보를보여주는페이지");
		return "mypage/등록한여행지의정보를보여주는페이지";
	}

// 북마크페이지.html 프론트 작업용 임시
	@GetMapping("/북마크페이지")
	public String 북마크페이지() {
		System.out.println("북마크페이지");
		return "mypage/북마크페이지";
	}

// 항공권(여행정보)등록페이지.html 프론트 작업용 임시
	@GetMapping("/항공권(여행정보)등록페이지")
	public String 항공권여행정보등록페이지() {
		System.out.println("항공권(여행정보)등록페이지");
		return "mypage/항공권(여행정보)등록페이지";
	}
	
// 항공권(여행정보)등록페이지.html 프론트 작업용 임시
	@GetMapping("/출국일자등록")
	public String 출국일자등록() {
		System.out.println("항공권(여행정보)등록페이출국일자등록");
		return "mypage/출국일자등록";
	}

}
