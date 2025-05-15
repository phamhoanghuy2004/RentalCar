package com.javaweb.service;

import java.util.Date;
import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ContractInfor;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.UpdateContractRequest;
import com.javaweb.entity.ContractEntity;
import java.util.List;

public interface ContractService {
	public List<ContractInfor> getAllContracts();
	public ResultDTO<ContractDTO> upDatePayment (Long idContract, Date date, Long price);
	public ContractEntity getContract (Long id);
	public ResultDTO updateStatus(UpdateContractRequest request);
}
