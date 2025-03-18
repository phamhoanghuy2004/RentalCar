package com.javaweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarDTO;




public interface CarService {
	public List<CarDTO> getCarOfBrandActive(int idBrand);
	public int updateLogo(int id, MultipartFile file)  throws IOException;
	Object lessThanSevenDay();
	Object topTen();
}
