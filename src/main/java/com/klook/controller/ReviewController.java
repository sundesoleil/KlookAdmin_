package com.klook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.klook.service.ProductService;
import com.klook.service.ReviewService;
import com.klook.vo.ProductVO;
import com.klook.vo.ReviewVO;

@Controller
public class ReviewController {
	@Autowired
	ReviewService service;
	
	@GetMapping("/review")
	public String getReviews(Model model) {
		model.addAttribute("menu_num",5);
		List<ReviewVO> reviewList = service.selectReviews(null);
		
		model.addAttribute("reviewList", reviewList);
		return "/review/list";
	}
}
