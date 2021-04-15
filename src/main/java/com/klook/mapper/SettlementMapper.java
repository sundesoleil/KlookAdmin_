package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.SettlementVO;

@Mapper
public interface SettlementMapper {
	public List<SettlementVO> selectSettlement();
	public void deleteSettlement(Integer seq);
	public SettlementVO selectSettlementBySeq(Integer seq);
}
