package com.fishing.www.security.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fishing.www.login.dto.UserDto;
import com.fishing.www.login.mapper.LoginMapper;
import com.fishing.www.login.service.LoginService;

/*
"/login" 요청이 오면 자동으로 UserDetailsService 타입으로 Ioc되어있는 loadUserByUsername 함수가 실행됨
loadUserByUsername 실행 시 패스워드 체크가 되지만 패스워드를 체크하는 코드가 보이지 않는다.
AuthenticationManager.authenticate(Authentication)을 호출하면 스프링 시큐리티에 내장된 AuthenticationProvider의 authenticate() 메서드가 호출되는데, 
이 중에서 DaoAuthenticationProvider.additionalAuthenticationChekcs(UserDetails, UsernamePasswordAuthenticationToken) 메서드에 다음과 같은 코드가 있다.
즉 아래코드에서 패스워드 체크를 진행한다. 내부 프로세스에서 자동으로 실행되는 것이다. 
///////////////////////////////////////////////////////////////////////////////////////////////
String presentedPassword = authentication.getCredentials().toString();

	if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
		logger.debug("Authentication failed: password does not match stored value");

		throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
	}
///////////////////////////////////////////////////////////////////////////////////////////////
	*/
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserDetailsService= "+username);
		UserDto user = null;
		try {
			user = loginService.getUser(username);
			
		} catch (Exception e) {
			System.out.println("PrincipalDetail= "+e);
			e.printStackTrace();
		};

		if(user == null) {
			throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");
		}
		return user;
		
	
	}

}
