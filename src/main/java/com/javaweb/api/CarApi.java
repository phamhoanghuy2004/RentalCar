package com.javaweb.api;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.BookingRequest;
import com.javaweb.beans.CarResponse;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.entity.CarEntity;
import com.javaweb.service.CarService;
import com.nimbusds.jose.JOSEException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/car")
@Tag(name = "Car API", description = "Các API liên quan đến xe")
public class CarApi {
	@Autowired
	private CarService carService;
	
	@Operation(
		summary = "Lấy danh sách xe của 1 hãng xe còn hoạt động"
	)
	@GetMapping("/brand/{idBrand}")
	public Object getCarByBrandActive(@PathVariable("idBrand") Long idBrand) {
		List<CarDTO> listCar = carService.getCarOfBrandActive(idBrand);
		return listCar;
	}
	
	@Operation(
			summary = "Lấy danh sách các xe mới nhất của hệ thống"
		)
	@GetMapping(value = "/new")
	public Object getNewCar() {
		List<CarDTO> listCar = carService.getNewCar();
		return listCar;
	}
	
	@Operation(
			summary = "Lấy danh sách các xe đang được giảm giá của hệ thống"
		)
	@GetMapping(value = "/sale")
	public Object getSaleCar() {
		List<CarDTO> listCar = carService.getSaleCar();
		return listCar;
	}
	
	
	@Operation(
			summary = "Lấy danh sách tất cả các xe đang hoạt động"
		)
	@GetMapping
	public Object getAllCar() {
		List<CarDTO> listCar = carService.getAllCar();
		return listCar;
	}
	
	
	@Operation(
			summary = "Lấy danh sách tất cả các xe"
		)
	@GetMapping(value = "/GetAll")
	public Object getAllCarNoStatus() {
		List<CarResponse> listCar = carService.getAllCarNoStatus();
		return listCar;
	}
	
	
	@Operation(
			summary = "Tìm kiếm xe theo bộ lọc",
			description = "Có các tham số như: brand-String, location-String, carPriceFrom-Long, carPriceTo-Long"
		)
	@GetMapping(value = "/findCar")
	public Object searchCar (@RequestParam Map<String,Object> params )  {
		List<CarDTO> listCar = carService.findCar(params);
		return listCar;
	}
	
	@Operation(
			summary = "Tìm kiếm xe theo tên"
		)
	@GetMapping(value = "/findByNameActive")
	public Object findByName (@RequestParam(value = "name", required = false) String name){
		List<CarDTO> listCar = carService.getCarByNameActive(name);
		return listCar;
	}
	
	@Operation(
			summary = "Thêm xe vào hệ thống"
		)
    @PostMapping("/insertCar")
    public ResponseEntity<?> InsertCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.insertCar(carRequest);
    	return response;
    }
    
	@Operation(
			summary = "Đặt thuê xe có xác thực JWT cần có token bằng cách đăng nhập"
		)
    @PostMapping("/book")
    public Object bookingCar (@RequestBody @Valid BookingRequest bookingRequest, @RequestHeader (value = "Authorization", required = false ) String token) throws JOSEException, ParseException {
    	ResultDTO result = carService.bookingCar(bookingRequest,token);
    	return result;
    }
    
	
	@Operation(
			summary = "Cập nhật thông tin xe"
		)
    @PutMapping("updateCar")
    public ResponseEntity<?> UpdateCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.updateCar(carRequest);
    	return response;
    }
    
	@Operation(
			summary = "Xóa xe"
		)
    @DeleteMapping("deleteCar/{id}")
    public ResponseEntity<?> UpdateCar(@PathVariable("id") Long id){
    	ResponseEntity response = carService.deleteCar(id);
    	return response;
    }

}
