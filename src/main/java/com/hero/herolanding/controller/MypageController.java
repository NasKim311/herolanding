package com.hero.herolanding.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.UpdateMemberDTO;
import com.hero.herolanding.service.BoardService;
import com.hero.herolanding.service.MypageService;
import com.hero.herolanding.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController {

	private final MypageService mypageService;
	private final BoardService boardService;

//--------<indexPage() / 로고 클릭시 메인화면으로 이동 메서드>-------------------------------------------------------------------------------------	
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

// --------<updateMemberData() / 마이페이지 회원정보 수정 메서드>-------------------------------------------------------------------------------------
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

// --------<deleteMember() / 마이페이지 회원정보 삭제 메서드>-------------------------------------------------------------------------------------
	@GetMapping("/mypage/delete/{memberId}")
	public String deleteMember(@PathVariable String memberId, @RequestParam(defaultValue = "/") String redirectURL,
			HttpServletRequest request) {

		System.out.println("C" + memberId);

		mypageService.deleteMember(memberId);

		HttpSession session = request.getSession();
		session.removeAttribute(SessionConst.LOGIN_MEMBER);

		return "redirect:" + redirectURL;
	}

//--------<airplaneForm() / 항공권(여행정보)등록페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/airplaneForm")
	public String airplaneForm() {
		return "mypage/항공권(여행정보)등록페이지";
	}

//--------<addAirplaneForm() / 출국일자등록페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/addAirplaneForm")
	public String addAirplaneForm() {
		return "mypage/출국일자등록";
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

//--------<boardByMeForm() / 내가작성한게시글페이지 이동하면서 조회하는 메서드(페이징처리 동시 작용)>-------------------------------------------------------------------------------------	
	@GetMapping("/mypage/boardByMeForm/{page}")
	public String boardByMeForm(@PathVariable("page") int page, Model model, HttpServletRequest request) {
		System.out.println(page);

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");

		// 첫 페이지를 무조건 1로 만드는 조건(첫 페이지 지정 조건)
		if (page < 1) {
			page = 1;
		}

		// 마지막 페이지를 지정하는 조건
		if (page > (mypageService.BoardCountByMemberId(member.getMemberId()).size() / 10) + 1) {
			page = (mypageService.BoardCountByMemberId(member.getMemberId()).size() / 10) + 1;
		}

		model.addAttribute("current", page / 10); // 페이징 처리를 위한 변수

		if ((page / 10) * 10 + 10 < (mypageService.BoardCountByMemberId(member.getMemberId()).size() / 10) + 1) {
			model.addAttribute("last", 9);
		} else {
			model.addAttribute("last", ((mypageService.BoardCountByMemberId(member.getMemberId()).size() / 10) + 1) % 10);
		}
		
		model.addAttribute("list", boardService.findAll(page));
		model.addAttribute("WholeCount", (mypageService.BoardCountByMemberId(member.getMemberId()).size() / 10) + 1);
		model.addAttribute("now", page);

		return "mypage/내가작성한게시글페이지";
	}

//--------<replyByMeForm() / 내가작성한댓글목록리스트페이지 이동 메서드>-------------------------------------------------------------------------------------	
//	@GetMapping("/mypage/replyByMeForm")
//	public String replyByMeForm() {
//		return "mypage/내가작성한댓글목록리스트";
//	}

} // MypageController class
