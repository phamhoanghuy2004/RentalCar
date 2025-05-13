package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaweb.entity.PaymentEntity;

public interface PaymentRepository  extends JpaRepository<PaymentEntity,Long> {

}
