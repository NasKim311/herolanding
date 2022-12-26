package com.hero.herolanding.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hero.herolanding.service.AdminCountryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminHomeController {
	
	private final AdminCountryService adminService;
	

// 임시로 설정해놓은 경로(관리자페이지)
	@GetMapping("/admin")
	public String admin(Model model) {
		
		List<Long> member1 = adminService.findSignupDate();
		
		int membersize = member1.size();
		
		if( membersize > 7) {
			membersize = 7;
		}
		
		List<Long> member = new ArrayList<>(member1.subList(0, membersize));
		for(int i = 0; i<member.size(); i++) {
			model.addAttribute("member"+i, member.get(i).intValue());
			System.out.println(model);
		}
		
		return "admin/관리자페이지";
	}
	
// 회원관리.html
	//@GetMapping("/회원관리")
	//public String manager() {
		//System.out.println("회원관리");
		//return "admin/회원관리";
	//}
	
// 게시글관리.html
	@GetMapping("/게시글관리")
	public String board() {
		System.out.println("게시글관리");
		return "admin/게시글관리";
	}

// 신고글관리.html
	@GetMapping("/신고글관리")
	public String report() {
		System.out.println("신고글관리");
		return "admin/신고글관리";
	}

// 회원정보수정.html
	@GetMapping("/회원정보수정")
	public String member() {
		System.out.println("회원정보수정"); 
		return "admin/회원정보수정";
	}

}
