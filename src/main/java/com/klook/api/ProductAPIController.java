package com.klook.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klook.service.ProductImageService;
import com.klook.service.ProductService;
import com.klook.vo.ProductDetailImageVO;
import com.klook.vo.ProductVO;

@RestController
public class ProductAPIController {
	@Autowired
	ProductService service;
	@Autowired
	ProductImageService imageService;
	
	
	@PutMapping("/product")
	public Map<String, String> putProduct(@RequestBody ProductVO vo){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.insertProduct(vo);
	
		resultMap.put("status", "success");
		resultMap.put("prod_seq", vo.getKp_seq().toString());
		return resultMap;
	}
	@PutMapping("/product_img/{seq}")
	public Map<String, String> putProductImage(
			@RequestPart MultipartFile file,
			@PathVariable Integer seq,
			@RequestParam String city,
			@RequestParam String name
			) throws Exception{
		
		if(file.getOriginalFilename() == "") {
			return null;
		}
		
		ProductVO vo = new ProductVO();
		vo.setKp_name(name);
		vo.setKp_seq(seq);
		vo.setCity_name(city);
		
		return imageService.insertProductImage(file, vo);
	}
	@GetMapping("/product_img/{fileName}")
	public ResponseEntity<Resource> getProductImage(@PathVariable String fileName, 
			HttpServletRequest request) throws Exception{
		Resource resource = imageService.getProductImage(fileName, "/klook/product_img");
		
		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); // 절대 경로로 접근
		if(contentType == null) {
			contentType = "application/octet-stream"; // default
		}
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			// 한글명 파일 깨짐을 방지하기 위하여 filename 뒤에 *
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+resource.getFilename()+"\"")
			.body(resource);
	}
	@DeleteMapping("/product/{seq}")
	public Map<String, String> deleteProduct(@PathVariable Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		service.deleteProduct(seq);
		
		return resultMap;
	}
	@GetMapping("/product/{seq}")
	public Map<String, Object> getProduct(@PathVariable Integer seq){
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		ProductVO vo = service.selectProductBySeq(seq);
		List<ProductDetailImageVO> detailImgs = imageService.selectProdDetailImages(seq);
		
		if(vo != null) {
			resultMap.put("status", "success");
			resultMap.put("product", vo);
			resultMap.put("detailImgs", detailImgs);
		}
		else {
			resultMap.put("status", "failed");
			resultMap.put("reason", seq + "번 데이터는 존재하지 않습니다.");
		}
		return resultMap;
	}
	@PatchMapping("/product")
	public Map<String, String> patchProduct(@RequestBody ProductVO vo){
		Map<String, String> resultMap = new LinkedHashMap<String,String>();
		
		service.updateProduct(vo);
		
		resultMap.put("status", "success");
		resultMap.put("message", "수정되었습니다.");
		
		return resultMap;
	}
	
	@PutMapping("/product_img_detail/{seq}")
	public Map<String, String> productImgDetail(
			@PathVariable Integer seq,
			@RequestPart List<MultipartFile> img_files,
			@RequestParam String city,
			@RequestParam String name
			) throws Exception{
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		Path fileLocation = Paths.get("/klook/product_detail_imgs");
		for(MultipartFile file : img_files) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			fileName = city + "_" + name + "_" + fileName;
			Path target = fileLocation.resolve(fileName);
			target.normalize();
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
			
			ProductDetailImageVO vo = new ProductDetailImageVO();
			vo.setKpdi_path(fileName);
			vo.setKpdi_uri(fileName);
			vo.setKpdi_prod_seq(seq);
			
			imageService.insertProdDetailImage(vo);
		
		}
		resultMap.put("status", "success");
		resultMap.put("message", "상품 이미지가 추가되었습니다.");
		
		return resultMap;
	}
	@GetMapping("/product_detail_img/{fileName}")
	public ResponseEntity<Resource> getProductDetailImage(@PathVariable String fileName, 
			HttpServletRequest request) throws Exception{
		//Resource resource = imageService.getProductImage(fileName, "/klook/product_datail_imgs");
		
		Path prodImageLocation = Paths.get("/klook/product_detail_imgs");
		Path prodImagePath = prodImageLocation.resolve(fileName).normalize();
		Resource resource = new UrlResource(prodImagePath.toUri());
		
		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); // 절대 경로로 접근
		if(contentType == null) {
			contentType = "application/octet-stream"; // default
		}
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			// 한글명 파일 깨짐을 방지하기 위하여 filename 뒤에 *
			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+resource.getFilename()+"\"")
			.body(resource);
	}
	@DeleteMapping("/product_detail_img")
	public Map<String, String> deleteProductDetailImg(@RequestBody List<String> uris){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		for(String uri : uris) {
			imageService.deleteProdDetailImage(uri);
		}
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		return resultMap;
	}
	
}
