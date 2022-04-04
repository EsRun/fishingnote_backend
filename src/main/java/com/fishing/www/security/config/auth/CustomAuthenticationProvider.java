package com.fishing.www.security.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailsService uds;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = (String) auth.getPrincipal();
		String password = (String) auth.getCredentials();
		
		UserDetails user =uds.loadUserByUsername(username);
		
		if(!passwordEncoder.matches(password,  user.getPassword())) {
			throw new BadCredentialsException(user.getUsername() + "Invalid password");
		}
		
		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());

	}

	// 토큰 타입과 일치할 때 인증
	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
