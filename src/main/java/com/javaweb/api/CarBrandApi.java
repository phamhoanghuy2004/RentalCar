package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarBrandDTO;
import com.javaweb.service.CarBrandService;

@RestController
@RequestMapping(value = "/api/carbrand")
public class CarBrandApi {
	
	@Autowired
	private CarBrandService carBrandService;
	
	
	@GetMapping
	public Object GetAllCarBrand () {
		List<CarBrandDTO> listCarBrandDTO = carBrandService.getAllCarBrandActive();
		return listCarBrandDTO;
	}
	
	 @PostMapping("/updateLogoBrand/{id}")
	    public ResponseEntity<?> updateCarLogo(@PathVariable("id") int brandId, @RequestParam("file") MultipartFile file) {
	        try {
	            if (carBrandService.updateLogo(brandId, file) == 1) {
	            	return ResponseEntity.ok("Cập nhật logo thành công!");
	            }
	            else {
	            	return ResponseEntity.badRequest().body("Không thể cập nhật");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().body("Lỗi khi cập nhật logo: " + e.getMessage());
	        }
	    }
}
