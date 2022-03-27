package com.fishing.www.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fishing.www.member.dto.MemberDto;

@Mapper
public interface MemberMapper {
	
	// 회원 가입
	public int insertUser(MemberDto memberDto);
		
}
