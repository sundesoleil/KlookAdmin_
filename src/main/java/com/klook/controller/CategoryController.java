package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klook.service.CategoryService;
import com.klook.vo.CategoryVO;

@Controller
public class CategoryController {
	@Autowired
	CategoryService service;
	
	@GetMapping("/main_category")
	public String getMainCategory(Model model) {
		model.addAttribute("menu_num", 1);
		
		List<CategoryVO> main_list = service.selectMainCategories();
		model.addAttribute("main_list", main_list);
		
		return "/category/main_list";
	}
	@GetMapping("/sub_category")
	public String getSubCategory(Model model) {
		model.addAttribute("menu_num", 2);
		
		List<CategoryVO> sub_list = service.selectSubCategories();		model.addAttribute("sub_list", sub_list);
		
		return "/category/sub_list";
	}
}
