package com.fishing.www.security.config.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		/*
		List<String> roleNames = new ArrayList<>();
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});

		if(roleNames.contains("ROLE_USER")) {
			System.out.println("USER");
		}
		else if(roleNames.contains("ROLE_ADMIN")) {
			System.out.println("ADMIN");
		}
		else {
			System.out.println("ANNONYMOUS");
		}
		*/
		// IP, 세션 ID
			WebAuthenticationDetails web = (WebAuthenticationDetails) auth.getDetails();
			System.out.println("IP : " + web.getRemoteAddress());
			System.out.println("Session ID : " + web.getSessionId());
			
			// 인증 ID
			System.out.println("name : " + auth.getName());
			
			// 권한 리스트
			List<GrantedAuthority> authList = (List<GrantedAuthority>) auth.getAuthorities();
			System.out.print("권한 : ");
			for(int i = 0; i< authList.size(); i++) {
				System.out.print(authList.get(i).getAuthority() + " ");
			}
			System.out.println();
		
		response.sendRedirect("/");
	}

}
