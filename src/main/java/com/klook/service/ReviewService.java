package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.ReviewMapper;
import com.klook.vo.ReviewVO;

@Service
public class ReviewService {
	@Autowired
	ReviewMapper mapper;
	
	public List<ReviewVO> selectReviews(Integer offset, String keyword, String type){
		
		//offset = offset * 10;
		List<ReviewVO> reviewList = mapper.selectReviews(offset, keyword, type);
		
		Integer total = this.selectReviewCount("%%", null);
		for(int i = 0; i < reviewList.size(); i++) {
			reviewList.get(i).setNo(total-i-offset);
		}
		
		return reviewList;
	}
	public Integer selectReviewCount(String keyword, String type) {
		return mapper.selectReviewCount(keyword, type);
	}
	public void deleteReview(Integer seq) {
		mapper.deleteReview(seq);
	}
	public ReviewVO selectReviewBySeq(Integer seq) {
		return mapper.selectReviewBySeq(seq);
	}
}
