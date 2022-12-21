package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hero.herolanding.service.SendMessageService;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class SendsmsController {
	
	private final SendMessageService sendMessageService;
	
	@ResponseBody @GetMapping("/phoneCheck")
	public String sendSMS(@RequestParam("phone") String userPhoneNumber) { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성
		
		sendMessageService.certifiedPhoneNumber(userPhoneNumber,randomNumber);
		System.out.println("샌드컨트롤러 들어옴");
		System.out.println("userPhoneNumber : "+ userPhoneNumber);
		return Integer.toString(randomNumber);
	}
}
