package com.javaweb.service;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.beans.CarDTO;




public interface CarService {
	public List<CarDTO> getCarOfBrandActive(Long idBrand);
	public List<CarDTO> getNewCar();
	public List<CarDTO> getSaleCar();
	public List<CarDTO> getAllCar();
//	public int updateLogo(int id, MultipartFile file)  throws IOException;
//	Object lessThanSevenDay();
//	Object topTen();
}
