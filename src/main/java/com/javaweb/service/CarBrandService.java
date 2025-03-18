package com.javaweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarBrandDTO;

public interface CarBrandService {
	public List<CarBrandDTO> getAllCarBrand();
	public List<CarBrandDTO> getAllCarBrandActive();
	public int updateLogo(int id, MultipartFile file)  throws IOException;

}
