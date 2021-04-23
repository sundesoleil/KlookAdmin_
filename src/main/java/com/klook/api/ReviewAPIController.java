package com.klook.api;


import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klook.service.ReviewService;
import com.klook.vo.ReviewVO;

@RestController
public class ReviewAPIController {
	@Autowired
	ReviewService service;
	
	@DeleteMapping("/review/{seq}")
	public Map<String, String> deleteReview(@PathVariable Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteReview(seq);
		
		return resultMap;
	}
	@GetMapping("/review_cnt")
	public Map<String, Integer> getReviewCnt(
			@RequestParam @Nullable String keyword,
			@RequestParam @Nullable String type
			){
		if(keyword == null) keyword = "%%";
		else keyword = "%"+keyword+"%";
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		Integer count = service.selectReviewCount(keyword, type);
		resultMap.put("count", count);
		return resultMap;
	}
	
	@GetMapping("/review/{seq}")
	public Map<String, Object> getReview(@PathVariable Integer seq){
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		ReviewVO vo = service.selectReviewBySeq(seq);
		
		if(vo != null) {
			resultMap.put("status", "success");
			resultMap.put("product", vo);
		}
		else {
			resultMap.put("status", "failed");
			resultMap.put("reason", seq + "번 데이터는 존재하지 않습니다.");
		}
		return resultMap;
	}
}
