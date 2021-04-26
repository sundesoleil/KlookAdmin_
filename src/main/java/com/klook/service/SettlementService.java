package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.SettlementMapper;
import com.klook.vo.ReviewVO;
import com.klook.vo.SettlementVO;

@Service
public class SettlementService {
	@Autowired
	SettlementMapper mapper;
	
	public List<SettlementVO> selectSettlement(Integer offset, String keyword, String type){
		List<SettlementVO> settlementList = mapper.selectSettlement(offset, keyword, type);
		
		Integer total = this.selectSettlementCount("%%", null);
		for(int i = 0; i < settlementList.size(); i++) {
			settlementList.get(i).setNo(total-i-offset);
		}
		
		return settlementList;
	}
	public Integer selectSettlementCount(String keyword, String type) {
		return mapper.selectSettlementCount(keyword, type);
	}
	public void deleteSettlement(Integer seq) {
		mapper.deleteSettlement(seq);
	}
	public SettlementVO selectSettlementBySeq(Integer seq) {
		return mapper.selectSettlementBySeq(seq);
	}
}
