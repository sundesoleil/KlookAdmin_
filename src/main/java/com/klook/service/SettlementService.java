package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.SettlementMapper;
import com.klook.vo.SettlementVO;

@Service
public class SettlementService {
	@Autowired
	SettlementMapper mapper;
	
	public List<SettlementVO> selectSettlement(SettlementVO vo){
		return mapper.selectSettlement();
	}
	public void deleteSettlement(Integer seq) {
		mapper.deleteSettlement(seq);
	}
	public SettlementVO selectSettlementBySeq(Integer seq) {
		return mapper.selectSettlementBySeq(seq);
	}
}
