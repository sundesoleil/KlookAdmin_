package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klook.service.SettlementService;
import com.klook.vo.SettlementVO;

@Controller
public class SettlementController {
	@Autowired
	SettlementService service;
	
	@GetMapping("/settlement")
	public String getSettlement(
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
		if(type == null) type = "number";
		
		
		List<SettlementVO> settlementList = service.selectSettlement(offset, keyword, type);
		model.addAttribute("settlementList", settlementList);
		model.addAttribute("menu_num", 5);
		
		return "/settlement/list";
	}
}
