package com.fishing.www.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fishing.www.login.dto.UserDto;

@Controller
public class IndexController {
	
	@GetMapping({"", "/"})
	public String index() {
		return "index/index";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	@GetMapping("/loginForm")
	public  String loginForm() {
		return "login/loginForm";
	}
	
	@GetMapping("/joinForm")
	public  String joinForm() {
		return "member/joinForm";
	}
	
	@PostMapping("/join")
	public @ResponseBody String join(UserDto userDto) {
		
		userDto.setRole("ROLE_USER");
		System.out.println(userDto);
		return "join";
	}
	

}
