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


import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.service.CarService;


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
	
	@GetMapping(value = "/findCar")
	public Object searchCar (@RequestParam Map<String,Object> params )  {
		List<CarDTO> listCar = carService.findCar(params);
		return listCar;
	}

    @PostMapping("/insertCar")
    public ResponseEntity<?> InsertCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.insertCar(carRequest);
    	return response;
    }

}
