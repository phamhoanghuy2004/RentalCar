package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ContractInfor;
import com.javaweb.service.ContractService;


@RestController
@RequestMapping(value = "/api/contract")
public class ContractAPI {
	@Autowired
	private ContractService contractService;
	
	@GetMapping(value = "/getAll")
	public List<ContractInfor> getAllContract(){
		return contractService.getAllContracts();
	}
	
	@GetMapping (value = "/getByCustomerId")
	public Object getByCustomerId (@RequestParam(value = "customerId", required = false) Long customerId) {
		List<ContractDTO> result = contractService.getContractByCustomerId(customerId);
		return result;
	}
}
