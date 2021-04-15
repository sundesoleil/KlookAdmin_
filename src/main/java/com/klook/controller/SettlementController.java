package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klook.service.SettlementService;
import com.klook.vo.SettlementVO;

@Controller
public class SettlementController {
	@Autowired
	SettlementService service;
	
	@GetMapping("/settlement")
	public String getSettlement(Model model) {
		model.addAttribute("menu_num",6);
		List<SettlementVO> settlementList = service.selectSettlement(null);
		
		model.addAttribute("settlementList", settlementList);
		return "/settlement/list";
	}
}
