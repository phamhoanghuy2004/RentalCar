package com.javaweb.service.Impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.beans.CarBrandDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.converter.CarBrandDTOConverter;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.repository.CarBrandRepository;
import com.javaweb.service.CarBrandService;

@Service
public class CarBrandServiceImpl implements CarBrandService  {
	
	@Autowired
	private CarBrandRepository carBrandRepository;
	
	@Autowired
	private CarBrandDTOConverter carBrandDTOConverter;


	@Override
	public List<CarBrandDTO> getAllCarBrandActive() {
		List<CarBrandEntity> listCarBrandEntity = carBrandRepository.findByStatus("Active");
		List<CarBrandDTO> listCarBrandDTO = new ArrayList<>();
		listCarBrandDTO = carBrandDTOConverter.convertCarBrandDTO(listCarBrandEntity);
		return listCarBrandDTO;
	}


	@Override
	public ResultDTO<CarBrandEntity> insertBrand(CarBrandEntity brand) {
		ResultDTO<CarBrandEntity> result = new ResultDTO<CarBrandEntity>();
		try {
			carBrandRepository.save(brand);
			result.setStatus(true);
			result.setMessage("Thêm hãng xe thành công");
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(false);
			result.setMessage("Lỗi khi thêm hãng xe");
		}
		return result;
	}


	@Override
	public ResultDTO<CarBrandEntity> updateBrand(CarBrandEntity brand) {
	    ResultDTO<CarBrandEntity> result = new ResultDTO<>();

	    try {
	        Optional<CarBrandEntity> optional = carBrandRepository.findById(brand.getId());
	        if (optional.isPresent()) {
	            CarBrandEntity brandEntity = optional.get();
	            brandEntity.setCode(brand.getCode());
	            brandEntity.setDescription(brand.getDescription());
	            brandEntity.setLogo(brand.getLogo());
	            brandEntity.setName(brand.getName());
	            brandEntity.setStatus(brand.getStatus());
	            carBrandRepository.save(brandEntity);
	            result.setStatus(true);
	            result.setMessage("Cập nhật hãng xe thành công");
	        } else {
	            result.setStatus(false);
	            result.setMessage("Không tìm thấy hãng xe");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.setStatus(false);
	        result.setMessage("Lỗi khi cập nhật hãng xe");
	    }
	    return result;
	}



	@Override
	public ResultDTO<CarBrandEntity> deleteBrand(Long id) {
	    ResultDTO<CarBrandEntity> result = new ResultDTO<>();

	    try {
	        Optional<CarBrandEntity> optional = carBrandRepository.findById(id);
	        if (optional.isPresent()) {
	            CarBrandEntity brandEntity = optional.get();
	            brandEntity.setStatus("Deleted");
	            carBrandRepository.save(brandEntity);
	            result.setStatus(true);
	            result.setMessage("Xóa hãng xe thành công");
	        } else {
	            result.setStatus(false);
	            result.setMessage("Không tìm thấy hãng xe");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.setStatus(false);
	        result.setMessage("Lỗi khi xóa hãng xe");
	    }
	    return result;
	}


}
