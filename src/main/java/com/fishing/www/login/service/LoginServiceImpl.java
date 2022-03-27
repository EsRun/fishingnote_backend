package com.fishing.www.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fishing.www.login.dto.UserDto;
import com.fishing.www.login.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginMapper loginMapper;
	
	// 로그인
	public UserDto getUser(String username) throws Exception{
		return loginMapper.getUser(username);
	}
	
}
