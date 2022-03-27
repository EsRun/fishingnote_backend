package com.fishing.www.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fishing.www.member.dto.MemberDto;
import com.fishing.www.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원 가입
	public int insertUser(MemberDto memberDto) {
		memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
		memberDto.setRole("ROLE_USER");
		return memberMapper.insertUser(memberDto);
	}
		
}
