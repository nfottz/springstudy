package com.gdu.app13.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.app13.service.MemberService;

@RequestMapping("/user")
@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/login.do")
	public String login(HttpServletRequest request) {  	// 서비스로 넘겨줄 정보
		memberService.login(request);
		return "index";								// 로그인이 끝났으면 인덱스로 돌아가자
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		memberService.logout(session);
		return "index";
	}
	
}
