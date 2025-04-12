package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.service.CarService;


@RestController
@RequestMapping(value = "/api/car")
public class CarApi {
	@Autowired
	private CarService carService;
	
//	@GetMapping(value = "/lessthan7days")
//	public Object lessThanSevenDay(){
//		return carService.lessThanSevenDay();
//	}
//	
//	@GetMapping(value = "/topTen")
//	public Object topTen(){
//		return carService.topTen();
//	}
	
	@GetMapping
	public Object getCarByBrandActive(@RequestParam(value = "idBrand" , required = false) Long idBrand) {
		List<CarDTO> listCar = carService.getCarOfBrandActive(idBrand);
		return listCar;
	}
	
	@GetMapping(value = "/newCar")
	public Object getNewCar() {
		List<CarDTO> listCar = carService.getNewCar();
		return listCar;
	}
	
	@GetMapping(value = "/saleCar")
	public Object getSaleCar() {
		List<CarDTO> listCar = carService.getSaleCar();
		return listCar;
	}
	
//    @PostMapping("/updateLogoCar/{id}")
//    public ResponseEntity<?> updateCarLogo(@PathVariable("id") int carId, @RequestParam("file") MultipartFile file) {
//        try {
//            if (carService.updateLogo(carId, file) == 1) {
//            	return ResponseEntity.ok("Cập nhật logo thành công!");
//            }
//            else {
//            	return ResponseEntity.badRequest().body("Không thể cập nhật");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Lỗi khi cập nhật logo: " + e.getMessage());
//        }
//    }


    @PostMapping("insertCar")
    public ResponseEntity<?> InsertCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.insertCar(carRequest);
    	return response;
    }

	

}
