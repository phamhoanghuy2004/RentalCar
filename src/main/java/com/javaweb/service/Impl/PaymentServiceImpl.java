package com.javaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.PaymentDTO;
import com.javaweb.entity.PaymentEntity;
import com.javaweb.repository.PaymentRepository;
import com.javaweb.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<PaymentDTO> getAll() {
		List<PaymentDTO> paymentDTOs = new ArrayList<>();
		try {
			List<PaymentEntity> paymentEntities = paymentRepository.findAll();
			for(PaymentEntity paymentEntity : paymentEntities) {
				PaymentDTO dto = new PaymentDTO();
				dto.setId(paymentEntity.getId());
				dto.setDate(paymentEntity.getDate());
				dto.setPrice(paymentEntity.getPrice());
				paymentDTOs.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentDTOs;
	}


}
