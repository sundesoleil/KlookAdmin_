package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.MemberMapper;
import com.klook.vo.LoginVO;
import com.klook.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	MemberMapper mapper;
	
	public boolean loginMember(LoginVO vo) {
		return mapper.loginMember(vo) != null;
	}
	public List<MemberVO> selectMembers(Integer offset){
		offset = offset * 10;
		return mapper.selectMembers(offset);
	}
	public Integer selectMemberCount() {
		return mapper.selectMemberCount();
	}
	public void deleteMemberInfo(Integer seq) {
		mapper.deleteMemberInfo(seq);
	}
}
