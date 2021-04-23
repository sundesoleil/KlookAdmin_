package com.klook.api;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*import com.klook.service.CityImageService;*/
import com.klook.service.CityService;
import com.klook.service.ProductService;
import com.klook.vo.CityVO;


@RestController
public class CityAPIController {
	@Autowired
	CityService service;
	@Autowired
	ProductService prodService;
	/*
	 * @Autowired CityImageService imageService;
	 */
	
	@GetMapping("/api/insert_city")
	public Map<String, String> getInsertCity(@RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		boolean result = service.insertCity(name);
		if(result) {
			resultMap.put("status", "success");
			resultMap.put("message", "추가되었습니다.");
		}else {
			resultMap.put("status", "failed");
			resultMap.put("message", "[" + name + "]은/는 이미 존재합니다.");
		}
		return resultMap;
	}

	@DeleteMapping("/city")
	public Map<String, String> deleteCity(@RequestParam Integer seq){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		service.deleteCity(seq);
		
		resultMap.put("status", "success");
		resultMap.put("message", "삭제되었습니다.");
		
		return resultMap;
	}
	
	@PatchMapping("/city/{seq}") 
	public Map<String, String> patchCity(@PathVariable Integer seq, @RequestParam String name){
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		
		CityVO vo = new CityVO();
		vo.setKc_seq(seq);
		vo.setKc_name(name);
		boolean updateStatus= service.updateCity(vo);
		
		resultMap.put("status", "success");
		resultMap.put("message", "카테고리 정보가 변경되었습니다.");
		/*
		 * if(updateStatus) { resultMap.put("status", "success");
		 * resultMap.put("message", "카테고리 정보가 변경되었습니다."); }else {
		 * resultMap.put("status", "success"); resultMap.put("message",
		 * "카테고리 이름이 중복됩니다."); }
		 */
		service.updateCity(vo);
		return resultMap;
	}
	@GetMapping("/city_list")
	public List<CityVO> getCity(){
		return service.selectCities();
	}
	/*
	 * @PutMapping("/city_img/{seq}") public Map<String, String> putCityImage(
	 * 
	 * @RequestPart MultipartFile file,
	 * 
	 * @PathVariable Integer seq,
	 * 
	 * @RequestParam String city,
	 * 
	 * @RequestParam String name ) throws Exception{
	 * 
	 * if(file.getOriginalFilename() == "") { return null; }
	 * 
	 * CityVO vo = new CityVO(); vo.setKc_name(name); vo.setKc_seq(seq);
	 * vo.setKc_name(city);
	 * 
	 * return imageService.insertCityImage(file, vo); }
	 */
	/*
	 * @GetMapping("/city_img/{fileName}") public ResponseEntity<Resource>
	 * getCityImage(@PathVariable String fileName, HttpServletRequest request)
	 * throws Exception{ Resource resource = imageService.getCityImage(fileName,
	 * "/klook/city_img");
	 * 
	 * String contentType = null; contentType =
	 * request.getServletContext().getMimeType(resource.getFile().getAbsolutePath())
	 * ; // 절대 경로로 접근 if(contentType == null) { contentType =
	 * "application/octet-stream"; // default } return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType(contentType)) // 한글명 파일 깨짐을 방지하기 위하여
	 * filename 뒤에 * .header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment; filename*=\""+resource.getFilename()+"\"") .body(resource); }
	 */
	
	
}
