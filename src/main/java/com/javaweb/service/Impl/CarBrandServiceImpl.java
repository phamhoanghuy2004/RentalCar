package com.javaweb.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarBrandDTO;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.entity.CarEntity;
import com.javaweb.repository.CarBrandRepository;
import com.javaweb.repository.CarRepository;
import com.javaweb.service.CarBrandService;

@Service
public class CarBrandServiceImpl implements CarBrandService  {
	
	@Autowired
	private CarBrandRepository carBrandRepository;
	@Autowired
	private CarRepository carRepository;

	@Override
	public List<CarBrandDTO> getAllCarBrand() {
		List<CarBrandEntity> listCarBrandEntity = carBrandRepository.findAll();
		List<CarBrandDTO> listCarBrandDTO = new ArrayList<>();
		for(CarBrandEntity item : listCarBrandEntity  ) {
			CarBrandDTO tmp = new CarBrandDTO();
			tmp.setCategoryID(item.getId());
			tmp.setName(item.getName());
			tmp.setDescription(item.getDescription());
			tmp.setLogo(item.getLogo());
			
			listCarBrandDTO.add(tmp);
		}
		return listCarBrandDTO;
	}

	@Override
	public List<CarBrandDTO> getAllCarBrandActive() {
		List<CarBrandEntity> listCarBrandEntity = carBrandRepository.findByStatus(1);
		List<CarBrandDTO> listCarBrandDTO = new ArrayList<>();
		for(CarBrandEntity item : listCarBrandEntity  ) {
			CarBrandDTO tmp = new CarBrandDTO();
			tmp.setCategoryID(item.getId());
			tmp.setName(item.getName());
			tmp.setDescription(item.getDescription());
			tmp.setLogo(item.getLogo());
			
			// tinh so xe trong car brand
			List<CarEntity> listCatEntity = carRepository.findByBrand_IdAndStatus(item.getId(), "Available");
			int toTal = (listCatEntity != null ? listCatEntity.size() : 0);
			tmp.setTotal(toTal);
			
			listCarBrandDTO.add(tmp);
		}
		return listCarBrandDTO;
	}

	@Override
	public int updateLogo(int carid, MultipartFile file) throws IOException {
		  if (file.isEmpty()) {
		        return 0;
		    }
		    // Cập nhật mảng byte vào database (THAY VÌ LƯU TÊN FILE)
		    return carBrandRepository.updatePicutre(carid, file.getBytes());
	}

}
