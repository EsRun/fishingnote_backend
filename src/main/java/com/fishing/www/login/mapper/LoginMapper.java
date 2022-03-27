package com.fishing.www.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fishing.www.login.dto.UserDto;

@Mapper
public interface LoginMapper {
	
	// 로그인 유저 조회
	public UserDto getUser(String username) throws Exception;

}
