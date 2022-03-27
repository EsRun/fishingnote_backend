package com.fishing.www.member.service;

import com.fishing.www.member.dto.MemberDto;

public interface MemberService {
	
	// 회원 가입
	public int insertUser(MemberDto memberDto);
	
}
