package com.fishing.www.login.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fishing.www.login.dto.UserDto;
import com.fishing.www.login.service.LoginService;

@Controller
public class IndexController {
	
	@Autowired
	LoginService loginService;

	@Autowired
    private SessionRegistry sessionRegistry;
	
	@GetMapping({"", "/"})
	public String index() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<SessionInformation> allSessions = sessionRegistry.getAllSessions(authentication.getPrincipal(), true);

        for (SessionInformation session: allSessions) {
            System.out.println("사용자:" + session.getPrincipal());
            System.out.println("세션아이디:" + session.getSessionId());
            System.out.println("세션만료여부:" + session.isExpired());
            System.out.println("=======================================================");
        }
        
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
	
	@GetMapping("/login/loginForm")
	public  String loginForm() {
		//System.out.println("로그인 폼");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("auth= "+auth);
//		if(!auth.getAuthorities().equals("ROLE_ANONYMOUS")) {
//			return "redirect:/";
//		}
		return "login/loginForm";
	}
	/*
	@PostMapping("/login/login")
	public String login(UserDto userDto) throws Exception {
		System.out.println("ㅇㅇ");
		return "redirect:/login/loginForm";
	}
	*/
	@GetMapping("/404")
	public String error(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println("404 status= "+status);
		return "error/404";
	}
	
	@GetMapping("/expired")
	public @ResponseBody String expired() {
		return "세션 만료";
	}
}
