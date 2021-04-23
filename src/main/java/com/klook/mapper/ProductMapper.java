package com.klook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.klook.vo.ProductVO;
import com.klook.vo.SearchVO;

@Mapper
public interface ProductMapper {
	public void insertProduct(ProductVO vo);
	public List<ProductVO> selectProducts(SearchVO vo);
	public void deleteProduct(Integer seq);
	public ProductVO selectProductBySeq(Integer seq);
	public void updateProduct(ProductVO vo);
	public Integer selectProdByCity(Integer seq);
}
