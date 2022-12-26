//package com.hero.herolanding.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.hero.herolanding.domain.Member;
//import com.hero.herolanding.domain.Reply;
//import com.hero.herolanding.repository.LoginRepository;
//import com.hero.herolanding.service.MypageReplyService;
//import com.hero.herolanding.session.SessionConst;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class MypageReplyController {
//	
//	private final MypageReplyService mypageReplyService;
////	private final LoginRepository loginRepository;
//	
//	@GetMapping("/mypage/replyByMeForm")
//	public String reply(HttpServletRequest request, Model model) {
//		
//		// 세션에서 로그인한 회원의 넘버받아오기.
//		Member loginMember = new Member();
//		HttpSession session = request.getSession();
//		loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//		
////		List<Reply> selectAll = mypageReplyService.findAll(m.getMemberNum());
//		List<Reply> selectReplyContent = mypageReplyService.findreplyContent(loginMember.getMemberNum());
//		model.addAttribute("selectReplyContent", selectReplyContent );
//		
//		return "mypage/내가작성한댓글목록리스트"; 
//	}
//
//}
