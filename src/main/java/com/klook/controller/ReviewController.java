package com.klook.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klook.service.ProductService;
import com.klook.service.ReviewService;
import com.klook.vo.MemberVO;
import com.klook.vo.ProductVO;
import com.klook.vo.ReviewVO;

@Controller
public class ReviewController {
	@Autowired
	ReviewService service;
	
	@GetMapping("/review")
	public String getReviews(
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
		if(type == null) type = "title";
		
		List<ReviewVO> reviewList = service.selectReviews(offset, keyword, type);
		model.addAttribute("reviewList", reviewList);
		
		model.addAttribute("menu_num", 4);
		
		return "/review/list";
	}
}
