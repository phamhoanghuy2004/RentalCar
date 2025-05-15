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
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/car")
public class CarApi {
	@Autowired
	private CarService carService;
	
	
	@GetMapping("/brand/{idBrand}")
	public Object getCarByBrandActive(@PathVariable("idBrand") Long idBrand) {
		List<CarDTO> listCar = carService.getCarOfBrandActive(idBrand);
		return listCar;
	}
	
	@GetMapping(value = "/new")
	public Object getNewCar() {
		List<CarDTO> listCar = carService.getNewCar();
		return listCar;
	}
	
	@GetMapping(value = "/sale")
	public Object getSaleCar() {
		List<CarDTO> listCar = carService.getSaleCar();
		return listCar;
	}
	
	@GetMapping
	public Object getAllCar() {
		List<CarDTO> listCar = carService.getAllCar();
		return listCar;
	}
	
	@GetMapping(value = "/GetAll")
	public Object getAllCarNoStatus() {
		List<CarResponse> listCar = carService.getAllCarNoStatus();
		return listCar;
	}
	
	@GetMapping(value = "/findCar")
	public Object searchCar (@RequestParam Map<String,Object> params )  {
		List<CarDTO> listCar = carService.findCar(params);
		return listCar;
	}
	
	@GetMapping(value = "/findByNameActive")
	public Object findByName (@RequestParam(value = "name", required = false) String name){
		List<CarDTO> listCar = carService.getCarByNameActive(name);
		return listCar;
	}

    @PostMapping("/insertCar")
    public ResponseEntity<?> InsertCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.insertCar(carRequest);
    	return response;
    }
    
    @PostMapping("/book")
    public Object bookingCar (@RequestBody @Valid BookingRequest bookingRequest, @RequestHeader (value = "Authorization", required = false ) String token) throws JOSEException, ParseException {
    	ResultDTO result = carService.bookingCar(bookingRequest,token);
    	return result;
    }
    
    @PutMapping("updateCar")
    public ResponseEntity<?> UpdateCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.updateCar(carRequest);
    	return response;
    }
    
    @DeleteMapping("deleteCar/{id}")
    public ResponseEntity<?> UpdateCar(@PathVariable("id") Long id){
    	ResponseEntity response = carService.deleteCar(id);
    	return response;
    }

}
