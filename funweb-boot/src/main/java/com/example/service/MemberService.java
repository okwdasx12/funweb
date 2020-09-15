package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.MemberVo;
import com.example.mapper.MemberMapper;

@Service
@Transactional // 모든 메소드 각각이 한개의 트랜잭션 단위로 수행됨
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	public void insert(MemberVo vo) {
		memberMapper.insert(vo);
	}
	public boolean isIdDuplicated(String id) {
		boolean isIdDup = false;
		int count = memberMapper.countMemberById(id);
		if (count == 1) {
			isIdDup = true; // 아이디중복
		} else { // count == 0
			isIdDup = false; // 아이디중복아님
		}
		return isIdDup;
	}
	
	public int userCheck(String id, String passwd) {
		int check = -1; // -1: 아이디 없음, 0: 비밀번호 틀림, 1: 아이디 비밀번호 일치
		
		String dbPasswd = memberMapper.getPasswdById(id);
		System.out.println("dbPasswd = " + dbPasswd);
		
		if (dbPasswd != null) {
			if (dbPasswd.equals(passwd)) {
				check = 1;
			} else {
				check = 0;
			}
		} else { // dbPasswd == null
			check = -1;
		}
		
		return check;
	}
}
