package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.CityMapper;
import com.klook.vo.CityVO;

@Service
public class CityService {
	@Autowired
	CityMapper mapper;
	
	public boolean insertCity(String name) {
		Integer result = mapper.selectCityByName(name);
		if(result == 1) {
			return false;
		}
		mapper.insertCity(name);
		return true;
	}
	
	public List<CityVO> selectCities(){
		return mapper.selectCities();
	}

	public void deleteCity(Integer seq) {
		mapper.deleteCity(seq);
	}

	public boolean updateCity(CityVO vo) {
		if(vo.getKc_name() == null || vo.getKc_name() == "") {
			return false;
		}
		Integer n = mapper.selectCityByName(vo.getKc_name());
		if(n == 1) {
			return false;
		}
		mapper.updateCity(vo);
		
		return true;
	}
}
