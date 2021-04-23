package com.klook.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	public List<ReviewVO> selectReviews(Integer offset, String keyword, String type);
	public void deleteReview(Integer seq);
	public Integer selectReviewCount(String keyword, String type);
	public ReviewVO selectReviewBySeq(Integer seq);
}
