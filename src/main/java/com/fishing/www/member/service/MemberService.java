package com.fishing.www.member.service;

import com.fishing.www.member.dto.MemberDto;

public interface MemberService {
	
	// 회원 가입
	public int insertUser(MemberDto memberDto);
	
	// 아이디 중복 확인
	public int idCheck(String userid) throws Exception;
	
}
