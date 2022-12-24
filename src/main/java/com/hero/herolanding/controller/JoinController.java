package com.hero.herolanding.controller;

import java.util.List;
import java.util.regex.Pattern;

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
		
		if(!StringUtils.hasText(joinDTO.getIdCheck())) {
			bindingResult.addError(new FieldError("joinForm", "memberId", joinDTO.getIdCheck(), false, null, null , "아이디 중복 확인은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberPw())) {
			bindingResult.addError(new FieldError("joinForm", "memberPw", joinDTO.getMemberPw(), false, null, null , "비밀번호는 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getPassWordCheck())) {
			bindingResult.addError(new FieldError("joinForm", "memberPw", joinDTO.getPassWordCheck(), false, null, null , "비밀번호 중복 확인은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberName())) {
			bindingResult.addError(new FieldError("joinForm", "memberName", joinDTO.getMemberName(), false, null, null , "이름은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberNickName())) {
			bindingResult.addError(new FieldError("joinForm", "memberNickName", joinDTO.getMemberNickName(), false, null, null , "닉네임은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getNickNameCheck())) {
			bindingResult.addError(new FieldError("joinForm", "memberNickName", joinDTO.getNickNameCheck(), false, null, null , "닉네임 중복 확인은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberEmail())) {
			bindingResult.addError(new FieldError("joinForm", "memberEmail", joinDTO.getMemberEmail(), false, null, null , "이메일은 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getEmailCheck())) {
			bindingResult.addError(new FieldError("joinForm", "memberEmail", joinDTO.getEmailCheck(), false, null, null , "이메일 중복 확인은 필수입니다."));
		}
		
		String pattern2 = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String str2 = joinDTO.getEmailCheck();
		if(Pattern.matches(pattern2, str2)) {
			bindingResult.addError(new FieldError("joinForm", "memberEmail", joinDTO.getEmailCheck(), false, null, null , "이메일 형식에 맞춰주세요."));
		}
		
		if(!StringUtils.hasText(joinDTO.getMemberPhoneNum())) {
			bindingResult.addError(new FieldError("joinForm", "memberPhoneNum", joinDTO.getMemberPhoneNum(), false, null, null , "휴대전화번호는 필수입니다."));
		}
		
		if(!StringUtils.hasText(joinDTO.getSendMsg())) {
			bindingResult.addError(new FieldError("joinForm", "memberPhoneNum", joinDTO.getSendMsg(), false, null, null , "휴대전화인증은 필수입니다."));
		}
		
		if(!joinDTO.getMemberUsingAgree() == true ) {
			bindingResult.addError(new FieldError("joinForm", "memberUsingAgree", joinDTO.getMemberUsingAgree(), false, null, null , "약관동의는 필수입니다."));
		}
		
		if(!joinDTO.getMemberDataAgree() == true) {
			bindingResult.addError(new FieldError("joinForm", "memberDataAgree", joinDTO.getMemberDataAgree(), false, null, null , "개인정보수집동의는 필수입니다."));
		}
//		
//		if(joinDTO.getSendMsg() == "false") {
//			bindingResult.addError(new FieldError("joinForm", "sendMsg", joinDTO.getSendMsg(), false, null, null , "휴대폰 인증은 필수입니다."));
//		}	

		if(bindingResult.hasErrors()) {
			
			return "login/join";
		}
		
		System.out.println("===============================================================값 확인 : " + joinDTO.getSendMsg());
		// 오류 없을시
		Member member= new Member();
		member.setMemberId(joinDTO.getMemberId());
		member.setMemberPw(joinDTO.getMemberPw());
		member.setMemberName(joinDTO.getMemberName());
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
	
	// 중복된 아이디 찾기
	@RequestMapping(value = "/join/id", method = RequestMethod.POST)
	@ResponseBody
	public boolean join1(JoinIdNicknameDTO dto ) {
		
		List<Member>  checkId = joinService.findId(dto.getMemberId());
		
		System.out.println(checkId.size());
		
		if(checkId.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	// 중복된 닉네임 찾기
	@RequestMapping(value = "/join/nickname", method = RequestMethod.POST)
	@ResponseBody
	public boolean join2(JoinIdNicknameDTO dto ) {
		
		List<Member>  checkNickname = joinService.findNickName(dto.getMemberNickName());
		
		if(checkNickname.size() != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	// 중복된 이메일 찾기
		@RequestMapping(value = "/join/email", method = RequestMethod.POST)
		@ResponseBody
		public boolean join3(JoinIdNicknameDTO dto ) {
			
			List<Member>  checkEmail = joinService.findEmail(dto.getMemberEmail());
			
			System.out.println(checkEmail.size());
			
			if(checkEmail.size() != 0) {
				return true;
			}else {
				return false;
			}
		}
	
	
	
	
	
}
