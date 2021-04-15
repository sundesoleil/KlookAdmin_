package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klook.service.CityService;
import com.klook.vo.CityVO;

@Controller
public class CityController {
	@Autowired
	CityService service;
	
	@GetMapping("/city")
	public String getMainCategory(Model model) {
		model.addAttribute("menu_num", 3);
		
		List<CityVO> city = service.selectCities();
		model.addAttribute("city", city);
		
		return "/city/list";
	}
}
