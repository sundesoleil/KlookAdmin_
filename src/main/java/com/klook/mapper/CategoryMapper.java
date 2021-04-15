package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.CategoryVO;
import com.klook.vo.SearchVO;

@Mapper
public interface CategoryMapper {
	
	public void insertMainCategory(String name);
	public Integer selectMainCategoryByName(String name);
	public List<CategoryVO> selectMainCategories();
	public void deleteMainCategory(Integer seq);
	public void updateMainCategory(CategoryVO vo);
	
	public void insertSubCategory(CategoryVO vo);
	public CategoryVO selectSubCategoryBySeq(Integer seq);
	public List<CategoryVO> selectSubCategories();
	public void deleteSubCategory(Integer seq);
	public void updateSubCategory(CategoryVO vo);

}
