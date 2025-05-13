package com.javaweb.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.beans.AddressDTO;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.CarResponse;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.CarLineEntity;
import com.javaweb.entity.ImageEntity;
import com.javaweb.entity.VoucherEntity;

@Component
public class CarDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CarDTO> convertCarDTO (List<CarEntity> listCarEntity){
		List<CarDTO> listCarDTO = new ArrayList<>();
		for (CarEntity car : listCarEntity) {
			CarDTO carDTO = new CarDTO();
			carDTO = modelMapper.map(car,CarDTO.class); // map nhung thang co ban
			
			
			AddressEntity addressEntity = car.getCarAddress();
			if (addressEntity != null) {
				AddressDTO address = modelMapper.map(addressEntity, AddressDTO.class);
				carDTO.setAddressDTO(address);
			}
			
			List<String> pitures = new ArrayList<>();
			// lay anh
			List<ImageEntity> imgs = car.getImages();
			for (ImageEntity img : imgs) {
				pitures.add(img.getData());
			}
			
			String discount = "0";
			List<VoucherEntity> vouchers = car.getVouchers();
			if (vouchers != null  && vouchers.size() > 0) {
				 discount = vouchers.get(0).getValue().toString();
			}
			carDTO.setDiscount(discount);
			carDTO.setPictures(pitures);
			
			CarLineEntity carLineEntity = car.getLine();
			carDTO.setLine(carLineEntity.getName());
			
			CarBrandEntity carBrandEntity = car.getBrand();
			carDTO.setBrand(carBrandEntity.getName());
			
			listCarDTO.add(carDTO);
		} 
		return listCarDTO; 
	}
	
	public CarResponse mapCarEntityToResponse(CarEntity car) {
	    CarResponse response = new CarResponse();
	    response.setId(car.getId());
	    response.setName(car.getName());
	    response.setDescription(car.getDescription());
	    response.setIndentify(car.getIndentify());
	    response.setDateOfStart(car.getDateOfStart());
	    response.setPrice(car.getPrice());
	    response.setStatus(car.getStatus());
	    
	    if (car.getImages() != null) {
	        List<String> pictureUrls = car.getImages()
	            .stream()
	            .map(ImageEntity::getData)
	            .collect(Collectors.toList());
	        response.setPictures(pictureUrls);
	    }

	    // Địa chỉ: province, district, ward, street từ AddressEntity
	    if (car.getCarAddress() != null) {
	        response.setProvince(car.getCarAddress().getProvince());
	        response.setDistrict(car.getCarAddress().getDistrict());
	        response.setWard(car.getCarAddress().getWard());
	        response.setStreet(car.getCarAddress().getStreet());
	    }

	    // Dòng xe
	    if (car.getLine() != null) {
	        response.setLine(car.getLine().getName());
	    }

	    // Hãng xe
	    if (car.getBrand() != null) {
	        response.setBrand(car.getBrand().getName());
	    }

	    return response;
	}

}
