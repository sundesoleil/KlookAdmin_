package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.CityVO;

@Mapper
public interface CityMapper {
	public void insertCity(String name);
	public Integer selectCityByName(String name);
	public List<CityVO> selectCities();
	public void deleteCity(Integer seq);
	public void updateCity(CityVO vo);
}
