/*
 * package com.klook.service;
 * 
 * import java.nio.file.Files; import java.nio.file.Path; import
 * java.nio.file.Paths; import java.nio.file.StandardCopyOption; import
 * java.util.Calendar; import java.util.LinkedHashMap; import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.core.io.Resource; import
 * org.springframework.core.io.UrlResource; import
 * org.springframework.stereotype.Service; import
 * org.springframework.util.StringUtils; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.klook.mapper.CityImageMapper; import com.klook.vo.CityImageVO;
 * import com.klook.vo.CityVO;
 * 
 * @Service public class CityImageService {
 * 
 * @Autowired CityImageMapper mapper;
 * 
 * public Map<String, String> insertCityImage(MultipartFile file, CityVO vo)
 * throws Exception{ Map<String, String>resultMap = new LinkedHashMap<String,
 * String>(); Path cityImageLocation = Paths.get("/klook/city_img");
 * 
 * CityImageVO imgVO = new CityImageVO();
 * 
 * String fileName = StringUtils.cleanPath(file.getOriginalFilename());
 * 
 * // 이미지 이름에 사용할 수 없는 특수문자 체크 String regex =
 * "!\\\'#[$]%&\\(\\)\\{\\}@`[*]:[+];-.<>,\\^~|'\\[\\]";
 * 
 * fileName = vo.getKc_name().replaceAll(regex, "_") + "_" +
 * fileName.replaceAll(regex, "_"); imgVO.setKci_uri(fileName.substring(0,
 * fileName.indexOf(".")));
 * 
 * 
 * Calendar c = Calendar.getInstance(); String currentDate = ""+
 * c.get(Calendar.YEAR)+ (c.get(Calendar.MONTH)+1)+ c.get(Calendar.DATE)+
 * c.get(Calendar.HOUR_OF_DAY)+ c.get(Calendar.MINUTE);
 * 
 * fileName = currentDate + "_" + fileName; imgVO.setKci_path(fileName);
 * imgVO.setKci_city_seq(vo.getKc_seq()); imgVO.setKci_size(file.getSize());
 * imgVO.setKci_name(file.getOriginalFilename());
 * 
 * Path targetLocation = cityImageLocation.resolve(fileName);
 * Files.copy(file.getInputStream(), targetLocation,
 * StandardCopyOption.REPLACE_EXISTING);
 * 
 * mapper.insertCityImage(imgVO);
 * 
 * return resultMap; }
 * 
 * public Resource getCityImage(String fileName, String filePath) throws
 * Exception{ CityImageVO vo = mapper.selectCityImage(fileName); Path
 * cityImageLocation = Paths.get(filePath); Path cityImagePath =
 * cityImageLocation.resolve(vo.getKci_path()).normalize(); Resource resource =
 * new UrlResource(cityImagePath.toUri()); return resource; } }
 */