package com.hero.herolanding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

	@GetMapping("/")
	public String hello() {
		return "heromanager/관리자페이지";
	}
}
