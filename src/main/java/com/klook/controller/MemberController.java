package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klook.service.MemberService;
import com.klook.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@GetMapping("/member")
	public String getMember(
			@RequestParam @Nullable Integer offset,
			Model model, 
			@RequestParam @Nullable String keyword,
			@RequestParam @Nullable String type) {
		
		if(offset == null) offset = 0;
		if(keyword == null) {
			keyword = "%%";
		}else {
			keyword = "%"+keyword+"%";
		}
		if(type == null) type = "name";
		
		List<MemberVO> memberList = service.selectMembers(offset, keyword, type);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("menu_num", 6);
		
		return "/member/list";
	}
}
