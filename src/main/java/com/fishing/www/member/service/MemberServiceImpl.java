package com.fishing.www.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fishing.www.member.dto.MemberDto;
import com.fishing.www.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	MemberMapper memberMapper;
	
	// 회원 가입
	public int insertUser(MemberDto memberDto) {
		memberDto.setRole("ROLE_USER");
		memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
		return memberMapper.insertUser(memberDto);
	}
	
	// 아이디 중복 확인
	public int idCheck(String userid) throws Exception{
		return memberMapper.idCheck(userid);
	}
		
}
