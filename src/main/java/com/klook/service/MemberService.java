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
		return mapper.loginMember(vo) == 1;
	}
	public List<MemberVO> selectMembers(Integer offset, String keyword, String type){
		List<MemberVO> memberList = mapper.selectMembers(offset, keyword, type);
		
		Integer total = this.selectMemberCount("%%", null);
		for(int i = 0; i < memberList.size(); i++) {
			memberList.get(i).setNo(total-i-offset);
		}
		return memberList;
	}
	public Integer selectMemberCount(String keyword, String type) {
		return mapper.selectMemberCount(keyword, type);
	}
	public void deleteMemberInfo(Integer seq) {
		mapper.deleteMemberInfo(seq);
	}
}
