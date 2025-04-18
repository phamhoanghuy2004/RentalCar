package com.javaweb.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javaweb.beans.CarDTO;
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
			
			String address = "";
			AddressEntity addressEntity = car.getCarAddress();
			if (address != null) {
				address = addressEntity.getDistrict() + ", " + addressEntity.getProvince();
			}
			carDTO.setLocation(address);
			listCarDTO.add(carDTO);
			List<String> pitures = new ArrayList<>();
			// lay anh
			List<ImageEntity> imgs = car.getImages();
			for (ImageEntity img : imgs) {
				pitures.add(img.getData());
			}
			
			String discount = "";
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
		} 
		return listCarDTO; 
	}
}
