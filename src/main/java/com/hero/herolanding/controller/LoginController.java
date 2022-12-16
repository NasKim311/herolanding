package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hero.herolanding.dto.LoginDTO;
import com.hero.herolanding.service.LoginService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

//--------<loginForm() / 로그인 페이지 이동 메서드>-------------------------------------------------------------------------------------	
	@GetMapping("/login/loginForm")
	public String loginForm(Model model) {
		LoginDTO loginDTO = new LoginDTO();
		model.addAttribute("login", loginDTO);
		System.out.println("loginId : " + loginDTO.getLoginId());
		System.out.println("loginPw : " + loginDTO.getLoginPw());
		return "login/login.html";
	}

//--------<login() / 로그인 하는 메서드>-------------------------------------------------------------------------------------	
	@PostMapping("/login/loginForm")
	public String login(@ModelAttribute LoginDTO loginDTO  , Model model) {
		model.addAttribute("login", loginDTO);
		System.out.println("loginId : " + loginDTO.getLoginId());
		System.out.println("loginPw : " + loginDTO.getLoginPw());
		return "index";
	}

} // LoginController class
