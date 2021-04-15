package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.CategoryMapper;
import com.klook.vo.CategoryVO;
import com.klook.vo.SearchVO;

@Service
public class CategoryService {
	@Autowired
	CategoryMapper mapper;
	
	public boolean insertMainCategory(String name) {
		Integer result = mapper.selectMainCategoryByName(name);
		if(result == 1) {
			return false;
		}
		mapper.insertMainCategory(name);
		return true;
	}
	
	public List<CategoryVO> selectMainCategories(){
		return mapper.selectMainCategories();
	}

	public void deleteMainCategory(Integer seq) {
		mapper.deleteMainCategory(seq);
	}

	public boolean updateMainCategory(CategoryVO vo) {
		if(vo.getKmc_name() == null || vo.getKmc_name() == "") {
			return false;
		}
		Integer n = mapper.selectMainCategoryByName(vo.getKmc_name());
		if(n == 1) {
			return false;
		}
		mapper.updateMainCategory(vo);
		
		return true;
	}
	// 카테고리(소)
	public void insertSubCategory(CategoryVO vo) {
		mapper.insertSubCategory(vo);
	}
	public List<CategoryVO> selectSubCategories(){
		return mapper.selectSubCategories();

	}
	public CategoryVO selectSubCategoryBySeq(Integer seq) {
		CategoryVO vo = mapper.selectSubCategoryBySeq(seq);
		return vo;
	}
	public void updateSubCategory(CategoryVO vo) {
		mapper.updateSubCategory(vo);
	}
	public void deleteSubCategory(Integer seq) {
		mapper.deleteSubCategory(seq);
	}
}
