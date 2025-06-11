package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ContractInfor;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.UpdateContractRequest;
import com.javaweb.service.ContractService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/api/contract")
@Tag(name = "Contract API", description = "Các API liên quan đến hợp đồng thuê xe")
public class ContractAPI {
	@Autowired
	private ContractService contractService;
	
	@Operation(
			summary = "Lấy tất cả các hợp đồng thuê xe của khách hàng"
	)
	@GetMapping(value = "/getAll")
	public List<ContractInfor> getAllContract(){
		return contractService.getAllContracts();
	}
	
	@Operation(
			summary = "Cập nhật trạng thái hợp đồng thuê xe của khách hàng"
	)
	@PutMapping(value = "/update")
	public ResultDTO updateStatus(@RequestBody UpdateContractRequest request) {
		return contractService.updateStatus(request);
	}
	
	@Operation(
			summary = "Lấy tất cả hợp đồng của một khách hàng"
	)
	@GetMapping (value = "/getByCustomerId")
	public Object getByCustomerId (@RequestParam(value = "customerId", required = false) Long customerId) {
		List<ContractDTO> result = contractService.getContractByCustomerId(customerId);
		return result;

	}
    
}
