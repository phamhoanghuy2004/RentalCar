package com.javaweb.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.PaymentDTO;
import com.javaweb.entity.ContractEntity;
import com.javaweb.entity.PaymentEntity;


@Component
public class ContractDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CarDTOConverter carDTOConverter;
	
	@Autowired
	private CustomerDTOConverter customerDTOConverter;
	
    public ContractDTO convertToDTO(ContractEntity contractEntity) {
    	ContractDTO contractDTO =  modelMapper.map(contractEntity, ContractDTO.class);
    	List<CarDTO> listCar = carDTOConverter.convertCarDTO(contractEntity.getCars());
		CustomerDTO customer = customerDTOConverter.convertToDTO(contractEntity.getCustomer());
		contractDTO.setCars(listCar);
		contractDTO.setCustomerDTO(customer);
		
		PaymentEntity paymentEntity = contractEntity.getPayment_of_contract();
		PaymentDTO paymentDTO = null;
		if (paymentEntity != null) {
			paymentDTO = new PaymentDTO();
			paymentDTO.setId(paymentEntity.getId());
			paymentDTO.setPrice(paymentEntity.getPrice());
			paymentDTO.setDate(paymentEntity.getDate());
		}
		contractDTO.setPaymentDTO(paymentDTO);
    	return contractDTO;
    }
}
