package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	public List<ReviewVO> selectReviews();
	public void deleteReview(Integer seq);
	public ReviewVO selectReviewBySeq(Integer seq);
}
