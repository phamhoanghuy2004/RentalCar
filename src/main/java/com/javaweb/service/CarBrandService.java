package com.javaweb.service;


import java.util.List;
import com.javaweb.beans.CarBrandDTO;

public interface CarBrandService {
	public List<CarBrandDTO> getAllCarBrand();
	public List<CarBrandDTO> getAllCarBrandActive();
}
