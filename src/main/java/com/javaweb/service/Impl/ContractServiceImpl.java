package com.javaweb.service.Impl;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ContractInfor;
import com.javaweb.beans.ResultDTO;
import com.javaweb.converter.ContractDTOConverter;
import com.javaweb.entity.ContractEntity;
import com.javaweb.entity.PaymentEntity;
import com.javaweb.repository.ContractRepository;
import com.javaweb.repository.PaymentRepository;
import com.javaweb.service.ContractService;
import com.javaweb.entity.CarEntity;



@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private ContractDTOConverter contractDTOConverter;

	@Override
	public ResultDTO<ContractDTO> upDatePayment(Long idContract, Date date, Long price) {
		ContractEntity contractEntity = contractRepository.findById(idContract).orElse(null);
		
		PaymentEntity paymentEntity  = new PaymentEntity(); 
		paymentEntity.setContractEntity(contractEntity);
		paymentEntity.setDate(date);
		paymentEntity.setPrice(price);
		
		contractEntity.setPayment_of_contract(paymentEntity);
		contractEntity.setStatus("SUCCESS");
		
		paymentRepository.save(paymentEntity);
		contractRepository.save(contractEntity);
		// co the bat loi khong the luu hop dong o day 
		
		ResultDTO<ContractDTO> resultDTO = new ResultDTO<>();
		resultDTO.setStatus(true);
		resultDTO.setData(contractDTOConverter.convertToDTO(contractEntity));
		resultDTO.setMessage("Thanh toán hợp đồng thành công! ");
		
		// Trả về một thằng rsDTO
		
		return resultDTO;
	}

	@Override
	public ContractEntity getContract(Long id) {
		return contractRepository.findByIdAndStatus(id,"WAIT");
	}
	
	@Override
	public List<ContractInfor> getAllContracts() {
		List<ContractEntity> contractEntities = contractRepository.findAll();
		List<ContractInfor> contractDTOs = new ArrayList<ContractInfor>();
		for(ContractEntity contractEntity : contractEntities) {
			String customerName = contractEntity.getCustomer() != null ? contractEntity.getCustomer().getName() : null;
			
			for (CarEntity car : contractEntity.getCars()) {
				ContractInfor dto = new ContractInfor();
                
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
