package com.klook.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klook.service.SettlementService;
import com.klook.vo.SettlementVO;

@RestController
public class SettlementAPIController {
	@Autowired
	SettlementService service;
	
	@DeleteMapping("/settlement/{seq}")
	public Map<String, String> deleteSettlement(@PathVariable Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteSettlement(seq);
		
		return resultMap;
	}
	@GetMapping("/settlement/{seq}")
	public Map<String, Object> getSettlement(@PathVariable Integer seq){
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		SettlementVO vo = service.selectSettlementBySeq(seq);
		
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
