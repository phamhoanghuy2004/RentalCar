package com.javaweb.service;
import java.util.Date;

import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.entity.ContractEntity;

public interface ContractService {
	public ResultDTO<ContractDTO> upDatePayment (Long idContract, Date date, Long price);
	public ContractEntity getContract (Long id);
}
