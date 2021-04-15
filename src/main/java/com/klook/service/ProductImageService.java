package com.klook.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.klook.mapper.ProductImageMapper;
import com.klook.vo.ProductImageVO;
import com.klook.vo.ProductVO;

@Service
public class ProductImageService {
	@Autowired
	ProductImageMapper mapper;
	
	public Map<String, String> insertProductImage(MultipartFile file, ProductVO vo) throws Exception{
		Map<String, String>resultMap = new LinkedHashMap<String, String>();
		Path prodImageLocation = Paths.get("/klook/product_img");
		
		ProductImageVO imgVO = new ProductImageVO();
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		// 이미지 이름에 사용할 수 없는 특수문자 체크
		String regex = "!\\\'#[$]%&\\(\\)\\{\\}@`[*]:[+];-.<>,\\^~|'\\[\\]";
		
		fileName = vo.getCity_name().replaceAll(regex, "_") + "_" + vo.getKp_name() + "_" + fileName.replaceAll(regex, "_");
		imgVO.setKpi_uri(fileName.substring(0, fileName.indexOf(".")));
		
		
		Calendar c = Calendar.getInstance();
		String currentDate = ""+
							c.get(Calendar.YEAR)+
							(c.get(Calendar.MONTH)+1)+
							c.get(Calendar.DATE)+
							c.get(Calendar.HOUR_OF_DAY)+
							c.get(Calendar.MINUTE);
		
		fileName = currentDate + "_" + fileName;
		imgVO.setKpi_path(fileName);
		imgVO.setKpi_prod_seq(vo.getKp_seq());
		imgVO.setKpi_size(file.getSize());
		imgVO.setKpi_name(file.getOriginalFilename());
		
		Path targetLocation = prodImageLocation.resolve(fileName);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		
		mapper.insertProductImage(imgVO);
		
		return resultMap;
	}
	
	public Resource getProductImage(String fileName) throws Exception{
		ProductImageVO vo = mapper.selectProductImage(fileName);
		Path prodImageLocation = Paths.get("/klook/product_img");
		Path prodImagePath = prodImageLocation.resolve(vo.getKpi_path()).normalize();
		Resource resource = new UrlResource(prodImagePath.toUri());
		return resource;
	}
}
