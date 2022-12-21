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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hero.herolanding.domain.Member;
import com.hero.herolanding.dto.LoginDTO;
import com.hero.herolanding.service.LoginService;
import com.hero.herolanding.session.SessionConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

//--------<loginForm() / 로그인 페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/login/loginForm")
	public String loginForm(Model model) {
		LoginDTO loginDTO = new LoginDTO();
		model.addAttribute("loginDTO", loginDTO);
		return "login/login";
	}

//--------<login() / 로그인 하는 메서드>-------------------------------------------------------------------------------------	
	@PostMapping("/login/loginForm")
	public String login(@ModelAttribute LoginDTO loginDTO, /* BindingResult bindingResult, */ Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam(defaultValue = "/") String redirectURL) {
		System.out.println("일반로그인");

//		// 아이디 Validation Check
//		if (!StringUtils.hasText(loginDTO.getLoginId())) {
//			bindingResult.addError(
//					new FieldError("loginDTO", "loginId", loginDTO.getLoginId(), false, null, null, "아이디를 입력해주세요."));
//		}
//
//		// 비밀번호 Validation Check
//		if (!StringUtils.hasText(loginDTO.getLoginPw())) {
//			bindingResult.addError(
//					new FieldError("loginDTO", "loginPw", loginDTO.getLoginPw(), false, null, null, "비밀번호를 입력해주세요."));
//		}
//
//		// Validation Check 검증에 실패하면 다시 로그인 폼으로 이동하는 로직
//		if (bindingResult.hasErrors()) {
//			System.out.println("error = " + bindingResult);
//			return "login/login";
//		}

		// loginService Class login()사용
		Member loginMemberData = loginService.login(loginDTO.getLoginId(), loginDTO.getLoginPw());

		// 로그인 실패일 경우
		if (loginMemberData == null) {
			System.out.println("로그인실패시 Controller");
			model.addAttribute("msg", "로그인 실패");
			return "login/login";
		}

		// 로그인 성공일 경우
		HttpSession session = request.getSession(); // 세션 사용
		session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberData); // 세션에 로그인 회원정보 보관
		
		redirectAttributes.addFlashAttribute("msg", "로그인 성공");

		return "redirect:" + redirectURL;
	}

//--------<kakaoLogin() / 카카오 로그인 하는 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/kakao/loginForm")
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) {
		System.out.println("카카오로그인");
		loginService.getAccessToken(code);
		return "index";
	}
	
//--------<logout() / 로그아웃 하는 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/logout/index")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute(SessionConst.LOGIN_MEMBER);

		return "index";
	}

} // LoginController class
