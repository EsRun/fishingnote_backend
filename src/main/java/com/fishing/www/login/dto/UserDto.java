package com.fishing.www.login.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDto implements UserDetails{
	private int idx;
	private String userid;
	private String password;
	private String username;
	private String email;
	private String role;
	private Timestamp regDate;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "UserDto [idx=" + idx + ", userid=" + userid + ", password=" + password + ", username=" + username
				+ ", email=" + email + ", role=" + role + ", regDate=" + regDate + "]";
	}
	// 계정 권한 목록 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(role));
        return auth;
	}
	// 사용자 계정의 만료 유무(기본값 true, 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 사용자 계정의 잠김 유무 리턴(기본값 true, 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// 사용자 계정의 비밀번호 만료 유무(기본값 true, 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 유무(기본값 true, 활성화 됨)
	@Override
	public boolean isEnabled() {
		// 1년동안 로그인을 안하면 휴면계정으로 변경하기로 약속해놨을 경우
		// 현재시간 - 로그인시간 = 1년 초과 시 return false;
		return true;
	}
}
