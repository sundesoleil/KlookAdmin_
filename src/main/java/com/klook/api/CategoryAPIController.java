package com.klook.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klook.service.CategoryService;
import com.klook.vo.CategoryVO;

@RestController
public class CategoryAPIController {
	@Autowired
	CategoryService service;
	
	@GetMapping("/api/insert_main_category")
	public Map<String, String> getInsertMainCategory(@RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		boolean result = service.insertMainCategory(name);
		if(result) {
			resultMap.put("status", "success");
			resultMap.put("message", "추가되었습니다.");
		}else {
			resultMap.put("status", "failed");
			resultMap.put("message", "[" + name + "]은/는 이미 존재합니다.");
		}
		return resultMap;
	}

	@DeleteMapping("/main_category")
	public Map<String, String> deleteMainCategory(@RequestParam Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteMainCategory(seq);
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		return resultMap;
	}
	
	@PatchMapping("/main_category/{seq}") 
	public Map<String, String> patchMainCategory(@PathVariable Integer seq, @RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		CategoryVO vo = new CategoryVO();
		vo.setKmc_seq(seq);
		vo.setKmc_name(name);
		boolean updateStatus= service.updateMainCategory(vo);
		
		if(updateStatus) {
			resultMap.put("status", "success");
			resultMap.put("message", "카테고리 정보가 변경되었습니다.");
		}else {
			resultMap.put("status", "success");
			resultMap.put("message", "카테고리 이름이 중복됩니다.");
		}
		service.updateMainCategory(vo);
		return resultMap;
	}
	@GetMapping("/main_category_list")
	public List<CategoryVO> getMainCategory(){
		return service.selectMainCategories();
	}
	/*카테고리(소)*/
	@PutMapping("/sub_category")
	public Map<String, String> putSubCategory(@RequestBody CategoryVO vo){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.insertSubCategory(vo);

		resultMap.put("status", "success");
		//resultMap.put("sub_cate_seq", vo.getKsc_seq().toString());
		return resultMap;
	}
	@PatchMapping("/sub_category")
	public Map<String, String> patchSubCategory(@RequestBody CategoryVO vo){
		Map<String, String> resultMap = new LinkedHashMap<String,String>();
		
		service.updateSubCategory(vo);
		
		resultMap.put("status", "success");
		resultMap.put("message", "수정되었습니다.");
		
		return resultMap;
	}
	@DeleteMapping("/sub_category/{seq}")
	public Map<String, String> deleteSubCategory(@PathVariable Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteSubCategory(seq);
		
		return resultMap;
	}
	@GetMapping("/sub_category/{seq}")
	public Map<String, Object> getSubCategory(@PathVariable Integer seq){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		CategoryVO vo = service.selectSubCategoryBySeq(seq);
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
	@GetMapping("/sub_category_list")
	public List<CategoryVO> getSubCategory(){
		return service.selectSubCategories();
	}
	
}
