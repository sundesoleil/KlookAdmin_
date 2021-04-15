package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.LoginVO;
import com.klook.vo.MemberVO;

@Mapper
public interface MemberMapper {
	public Integer loginMember(LoginVO vo);
	public List<MemberVO> selectMembers(Integer offset);
	public Integer selectMemberCount();
	public void deleteMemberInfo(Integer seq);
}
