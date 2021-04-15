package com.klook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klook.mapper.ProductImageMapper;
import com.klook.mapper.ProductMapper;
import com.klook.vo.ProductVO;
import com.klook.vo.SearchVO;

@Service
public class ProductService {
	@Autowired
	ProductMapper mapper;
	@Autowired
	ProductImageMapper imageMapper;
	
	public void insertProduct(ProductVO vo) {
		mapper.insertProduct(vo);
	}
	public List<ProductVO> selectProducts(SearchVO vo){
		List<ProductVO> list = mapper.selectProducts(vo);
		
		String prefix = "/product_img/";
		list.forEach(item-> {
			String uri = imageMapper.selectProductImageURI(item.getKp_seq());
			if(uri != null) {
				item.setImage_uri(prefix+uri);
			}
		});
		
		return list;
	}
	public void deleteProduct(Integer seq) {
		mapper.deleteProduct(seq);
	}
	public ProductVO selectProductBySeq(Integer seq) {
		String imageName = imageMapper.selectProductImageName(seq);
		ProductVO vo = mapper.selectProductBySeq(seq);
		vo.setImage_name(imageName);
		return vo;
	}
	public void updateProduct(ProductVO vo) {
		mapper.updateProduct(vo);
	}
}
