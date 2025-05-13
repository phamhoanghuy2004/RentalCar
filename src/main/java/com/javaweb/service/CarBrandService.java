package com.javaweb.service;


import java.util.List;
import com.javaweb.beans.CarBrandDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.entity.CarBrandEntity;

public interface CarBrandService {
	public List<CarBrandDTO> getAllCarBrandActive();
	public ResultDTO<CarBrandEntity> insertBrand(CarBrandEntity brand);
	public ResultDTO<CarBrandEntity> updateBrand(CarBrandEntity brand);
	public ResultDTO<CarBrandEntity> deleteBrand(Long id);
}
