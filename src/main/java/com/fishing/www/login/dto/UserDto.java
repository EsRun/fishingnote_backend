package com.fishing.www.login.dto;

import java.sql.Timestamp;

public class UserDto {
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
}
