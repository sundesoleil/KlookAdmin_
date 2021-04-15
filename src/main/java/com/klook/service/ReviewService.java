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
	
	public List<ReviewVO> selectReviews(ReviewVO vo){
		return mapper.selectReviews();
	}
	public void deleteReview(Integer seq) {
		mapper.deleteReview(seq);
	}
	public ReviewVO selectReviewBySeq(Integer seq) {
		return mapper.selectReviewBySeq(seq);
	}
}
