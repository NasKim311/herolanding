package com.hero.herolanding.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.UpdateMemberDTO;
import com.hero.herolanding.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;

//--------<indexPage() / 로고 클릭 시 메인페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/index")
	public String indexPage(@RequestParam(defaultValue = "/") String redirectURL) {
		return "redirect:" + redirectURL;
	}

//--------<mypageForm() / 마이페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/mypageForm")
	public String mypageForm(Model model, HttpServletRequest request) {
		Member loginMember = new Member(); // 현재 로그인 한 데이터가 담기는 객체 생성

		HttpSession session = request.getSession();
		loginMember = (Member) session.getAttribute("loginMember");

		model.addAttribute("updateMemberDTO", loginMember);
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

//--------<updateMemberData() / 마이페이지 회원정보 수정 메서드>-------------------------------------------------------------------------------------	
	@PostMapping("/mypage/mypageForm")
	public String updateMemberData(@ModelAttribute UpdateMemberDTO updateMemberDTO, HttpServletRequest request) {
		Member updateMemberData = new Member();

		HttpSession session = request.getSession();
		updateMemberData = (Member) session.getAttribute("loginMember");

		updateMemberData.setMemberId(updateMemberDTO.getMemberId());
		updateMemberData.setMemberPw(updateMemberDTO.getMemberPw());
		updateMemberData.setMemberName(updateMemberDTO.getMemberName());
		updateMemberData.setMemberNickName(updateMemberDTO.getMemberNickName());
		updateMemberData.setMemberEmail(updateMemberDTO.getMemberEmail());
		updateMemberData.setMemberPhoneNum(updateMemberDTO.getMemberPhoneNum());

		Member NewMemberData = mypageService.updateMemberData(updateMemberData);

		session.setAttribute("loginMember", NewMemberData);

		return "mypage/마이페이지";
	}

} // MypageController class
