package com.fishing.www.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fishing.www.member.dto.MemberDto;
import com.fishing.www.member.mapper.MemberMapper;
import com.fishing.www.member.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/joinForm")
	public  String joinForm() {
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public String join(MemberDto memberDto) throws Exception {
		memberService.insertUser(memberDto);
		return "redirect:/joinForm";
	}
	
}
