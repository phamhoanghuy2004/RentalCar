package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.beans.AddressDTO;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.request.updateProfileRequest;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CustomerEntity;

@Component
public class CustomerDTOConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
    public CustomerDTO convertToDTO(CustomerEntity customerEntity) {
    	if (customerEntity == null) {
    	    return null;
    	}
    	CustomerDTO cusDTO =  modelMapper.map(customerEntity, CustomerDTO.class);
    	AddressEntity addressEntity = customerEntity.getCustomerAddress();
    	if (addressEntity != null) {
    	    AddressDTO addressDTO = modelMapper.map(addressEntity, AddressDTO.class);
    	    cusDTO.setAddressDTO(addressDTO);
    	} else {
    	    cusDTO.setAddressDTO(null); // hoặc bỏ qua nếu DTO mặc định null
    	}
    	return cusDTO;
    }
    
    public void convertToEntityFromRequest (updateProfileRequest request, CustomerEntity cus) {
    	modelMapper.map(request, cus);
    }
}
