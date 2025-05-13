package com.javaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.ContractDTO;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.ContractEntity;
import com.javaweb.repository.ContractRepository;
import com.javaweb.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService{
	@Autowired
	private ContractRepository contractRepository;
	
	@Override
	public List<ContractDTO> getAllContracts() {
		List<ContractEntity> contractEntities = contractRepository.findAll();
		List<ContractDTO> contractDTOs = new ArrayList<ContractDTO>();
		for(ContractEntity contractEntity : contractEntities) {
			String customerName = contractEntity.getCustomer() != null ? contractEntity.getCustomer().getName() : null;
			
			for (CarEntity car : contractEntity.getCars()) {
                ContractDTO dto = new ContractDTO();
                
                dto.setId(contractEntity.getId());
                dto.setStatus(contractEntity.getStatus());
                dto.setDateFrom(contractEntity.getDateFrom());
                dto.setDateTo(contractEntity.getDateTo());
                dto.setPrice(contractEntity.getPrice());
                dto.setNameCustomer(customerName);
                dto.setNameCar(car.getName());
                dto.setIndentifyCar(car.getIndentify());

                contractDTOs.add(dto);
            }
			
		}
		return contractDTOs;
	}
}
