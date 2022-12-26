//package com.hero.herolanding.controller;
//
//import java.io.UnsupportedEncodingException;
//
//import javax.mail.MessagingException;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.hero.herolanding.dto.MailDTO;
//import com.hero.herolanding.service.Mailservice;
//
//import lombok.RequiredArgsConstructor;
//
//@Controller
//@RequiredArgsConstructor
//public class MailController {
//
//	private final Mailservice mailService;
//    
//	
//	@GetMapping("/mail")
//	public String mail(Model model) {
//		model.addAttribute("mail" , new MailDTO());
//		return "mail";
//	}
//	
//    @PostMapping("mail")
//    public String mailConfirm(@RequestBody MailDTO emailDto) throws MessagingException, UnsupportedEncodingException {
//        String authCode = mailService.sendEmail(emailDto.getMemberEmail());
//        return authCode;
//    }
//
//}
