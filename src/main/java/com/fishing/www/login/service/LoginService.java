package com.fishing.www.login.service;

import com.fishing.www.login.dto.UserDto;

public interface LoginService {
	
	// 로그인
	public UserDto getUser(String username) throws Exception;

}
