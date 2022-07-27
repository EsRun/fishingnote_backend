package com.fishing.www.login.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDto implements UserDetails{
	/*
	□ Serializable(직렬화)
		- 프로그램에서 사용되는 데이터들은 연속적으로 위치해 있지 않고 내부적으로
		포인터에 의해 참조 되고 있는데, 이는 프로그램이 실행중인 컴퓨터에서만 인식할 수 있는 형태이다.
		다른 컴퓨터와 통신하며 데이터를 알맞게 전달하기 위해서는 이렇게 흩뿌려져 있는 데이터를 한 데 모아 
		포인터가 존재하지 않는 일련의 바이트 형태로 만들어서 보내야 하는데 이를 보고 직렬화 라고 한다.
	
	 Serializable을 상속하는 클래스 사용 시 serialVersionUID 변수를 선언 및 값 선언을 해야한다.
	 해당 변수를 사용하지 않을 경우 자동으로 생성되지만 이는 역직렬화 시에 오류를 유발할 수 있다.
	 
	 --- Serializable(직렬화) ---                                     --- DeSerializable(역직렬화) --- 
	 object -> byte array -> [ file, memory, database ] -> byte array -> object
	 */
	
	private static final long serialVersionUID = 1L;
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
