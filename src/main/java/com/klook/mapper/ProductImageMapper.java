package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.ProductDetailImageVO;
import com.klook.vo.ProductImageVO;

@Mapper
public interface ProductImageMapper {
	public void insertProductImage(ProductImageVO vo);
	public ProductImageVO selectProductImage(String uri);
	public String selectProductImageURI(Integer seq);
	public String selectProductImageName(Integer seq);
	public void insertProdDetailImage(ProductDetailImageVO vo);
	public List<ProductDetailImageVO> selectProdDetailImages(Integer seq);
	public void deleteProdDetailImage(String uri);
}
