package com.klook.api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klook.service.BookingService;
import com.klook.vo.BookingVO;
import com.klook.vo.CategoryVO;
import com.klook.vo.ProductVO;

@RestController
public class BookingAPIController {
	@Autowired
	BookingService service;
	
	@GetMapping("/booking/{seq}")
	public Map<String, Object> getBooking(@PathVariable Integer seq){
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		BookingVO vo = service.selectBookingBySeq(seq);
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
	@GetMapping("/booking_cnt")
	public Map<String, Integer> getMemberCnt(){
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap.put("count", service.selectBookingCount());
		return resultMap;
	}
	@DeleteMapping("/booking/{seq}")
	public Map<String, String> deleteBooking(@PathVariable Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteBooking(seq);
		
		return resultMap;
	}
	
}
