package com.hero.herolanding.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.JoinDTO;
import com.hero.herolanding.dto.JoinIdNicknameDTO;
import com.hero.herolanding.dto.LoginDTO;
import com.hero.herolanding.repository.JoinRepository;
import com.hero.herolanding.service.JoinService;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;
	private final JoinRepository joinRepository;
	
	
	@GetMapping("/joins/new")
	public String createForm(Model model) {
		model.addAttribute("joinDTO", new JoinDTO());
		return "login/join";
	}
	
	@PostMapping("/joins/new")
	public String create(@ModelAttribute JoinDTO joinDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		
		// 벨리데이션체크
		if(!StringUtils.hasText(joinDTO.getMemberId())) {
			bindingResult.addError(new FieldError("joinForm", "memberId", joinDTO.getMemberId(), false, null, null , "아이디는 필수입니다."));
		}
		
		if(joinDTO.getMemberId().length() < 3 || joinDTO.getMemberId().length() > 15   ) {
			bindingResult.addError(new FieldError("joinForm", "memberId", joinDTO.getMemberId(), false, null, null , "아이디는 3~15글자 입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberPw())) {
			bindingResult.addError(new FieldError("joinForm", "memberPw", joinDTO.getMemberPw(), false, null, null , "비밀번호는 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberNickName())) {
			bindingResult.addError(new FieldError("joinForm", "memberNickName", joinDTO.getMemberNickName(), false, null, null , "별명은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberEmail())) {
			bindingResult.addError(new FieldError("joinForm", "memberEmail", joinDTO.getMemberEmail(), false, null, null , "이메일은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberPhoneNum())) {
			bindingResult.addError(new FieldError("joinForm", "memberPhoneNum", joinDTO.getMemberPhoneNum(), false, null, null , "휴대전화번호는 필수입니다."));
		}
		
		if(!joinDTO.getMemberUsingAgree() == true ) {
			bindingResult.addError(new FieldError("joinForm", "memberUsingAgree", joinDTO.getMemberUsingAgree(), false, null, null , "약관동의는 필수입니다."));
		}
		
		if(!joinDTO.getMemberDataAgree() == true) {
			bindingResult.addError(new FieldError("joinForm", "memberDataAgree", joinDTO.getMemberDataAgree(), false, null, null , "개인정보수집동의는 필수입니다."));
		}
		
		if(bindingResult.hasErrors()) {
			
			return "login/join";
		}
		
		// 오류 없을시
		Member member= new Member();
		member.setMemberId(joinDTO.getMemberId());
		member.setMemberPw(joinDTO.getMemberPw());
		member.setMemberNickName(joinDTO.getMemberNickName());
		member.setMemberEmail(joinDTO.getMemberEmail());
		member.setMemberPhoneNum(joinDTO.getMemberPhoneNum());
		member.setMemberUsingAgree(joinDTO.getMemberUsingAgree());
		member.setMemberDataAgree(joinDTO.getMemberDataAgree());
		member.setMemberAdvAgree(joinDTO.getMemberAdvAgree());
		
		member.setSignUpDate(joinDTO.getSignUpDate());
		
		joinService.saveJoin(member);
		
		
		
		return "redirect:/";
	}
	
	
//	@RequestMapping(value = "/join/id", method = RequestMethod.POST)
//	@ResponseBody
//	public String login(Model model,JoinIdNicknameDTO dto ) {
//		// @RequestParam String id , @RequestParam String password,
//		List<Join>  login = loginService.login(loginForm.getId(), loginForm.getPassword());
//		
//		if ( login == null) {
//			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요!!");
//			return "login/login";
//		}
//		
//		
//		if(login.get(0).getId() == loginForm.getId()) {
//			
//			HttpSession session = request.getSession();
//			session.setAttribute(SessionConst.login, login.get(0));
//			redirectAttributes.addFlashAttribute("msg", "로그인성공");
//		}
//		return "redirect:" + redirectURL;
//	}
	
	
	
	
	
	
	
}
