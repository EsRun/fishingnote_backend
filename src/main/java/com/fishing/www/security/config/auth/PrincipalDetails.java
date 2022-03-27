package com.fishing.www.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fishing.www.login.dto.UserDto;

// 로그인 진행이 완료되면 시큐리티 session을 만들어 준다(Security ContextHolder)
// 세션에 들어갈 수 있는 오브젝트가 정해져있음(오브젝트 타입 -> Authentication 타입의 객체)
// Authentication 안에는 User 정보가 있어야 함
// User 오브젝트의 타입은 -> UserDetails 타입의 객체
// 시큐리티 세션에 정보를 저장 -> 여기에 들어갈 수 있는 객체는 Authentication
// Authentication에 유저 정보를 저장할때 -> userDetails 타입의 객체가 필요

// Security Session 안에 Authentication 안에 Userdetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDto user;
	
	public PrincipalDetails(UserDto user) {
		this.user = user;
	}
	
	// 해당 유저의 권한을 리턴
	// 리턴타입이 Collection<GrantedAuthority> 이기 때문에 스트링 타입의 유저 권한을 리턴할 수 없음
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return user.getRole();
			}
			
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserid();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 유무
	@Override
	public boolean isEnabled() {
		
		// 1년동안 로그인을 안하면 휴면계정으로 변경하기로 약속해놨을 경우
		// 현재시간 - 로그인시간 = 1년 초과 시 return false;
		
		return true;
	}

}
