package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.SettlementVO;

@Mapper
public interface SettlementMapper {
	public List<SettlementVO> selectSettlement(Integer offset, String keyword, String type);
	public Integer selectSettlementCount(String keyword, String type);
	public void deleteSettlement(Integer seq);
	public SettlementVO selectSettlementBySeq(Integer seq);
}
