package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {

//--------<mypageForm() / 마이페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/mypageForm")
	public String mypageForm() {
		return "mypage/마이페이지";
	}
	
//--------<airplaneForm() / 항공권(여행정보)등록페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/airplaneForm")
	public String airplaneForm() {
		return "mypage/항공권(여행정보)등록페이지";
	}
	
//--------<spotForm() / 등록한여행지의정보를보여주는페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/spotForm")
	public String spotForm() {
		return "mypage/등록한여행지의정보를보여주는페이지";
	}
	
//--------<bookMarkForm() / 북마크페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/bookMarkForm")
	public String bookMarkForm() {
		return "mypage/북마크페이지";
	}
	
//--------<boardByMeForm() / 내가작성한게시글페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/boardByMeForm")
	public String boardByMeForm() {
		return "mypage/내가작성한게시글페이지";
	}

//--------<replyByMeForm() / 내가작성한댓글목록리스트페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/replyByMeForm")
	public String replyByMeForm() {
		return "mypage/내가작성한댓글목록리스트";
	}

	
} // MypageController class
