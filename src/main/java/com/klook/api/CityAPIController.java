package com.klook.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.klook.service.CityService;
import com.klook.vo.CityVO;


@RestController
public class CityAPIController {
	@Autowired
	CityService service;
	
	@GetMapping("/api/insert_city")
	public Map<String, String> getInsertCity(@RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		boolean result = service.insertCity(name);
		if(result) {
			resultMap.put("status", "success");
			resultMap.put("message", "추가되었습니다.");
		}else {
			resultMap.put("status", "failed");
			resultMap.put("message", "[" + name + "]은/는 이미 존재합니다.");
		}
		return resultMap;
	}

	@DeleteMapping("/city")
	public Map<String, String> deleteCity(@RequestParam Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteCity(seq);
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		return resultMap;
	}
	
	@PatchMapping("/city/{seq}") 
	public Map<String, String> patchCity(@PathVariable Integer seq, @RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		CityVO vo = new CityVO();
		vo.setKc_seq(seq);
		vo.setKc_name(name);
		boolean updateStatus= service.updateCity(vo);
		
		if(updateStatus) {
			resultMap.put("status", "success");
			resultMap.put("message", "카테고리 정보가 변경되었습니다.");
		}else {
			resultMap.put("status", "success");
			resultMap.put("message", "카테고리 이름이 중복됩니다.");
		}
		service.updateCity(vo);
		return resultMap;
	}
	@GetMapping("/city_list")
	public List<CityVO> getCity(){
		return service.selectCities();
	}
	
}
