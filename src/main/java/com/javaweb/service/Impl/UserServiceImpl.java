package com.javaweb.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.UserRequest;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.AddressRepository;
import com.javaweb.repository.RoleRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.UserService;
import com.javaweb.util.EmailService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CustomerEntity convertToEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, CustomerEntity.class);
    }
	
	public AddressEntity convertToAddressEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, AddressEntity.class);
    }
	
	@Override
	public void registerUser(UserRequest userRequest) {
		CustomerEntity customer = convertToEntity(userRequest);
		 if (userRepository.findByEmail(customer.getEmail()).isPresent()) {
	            throw new IllegalStateException("Email đã tồn tại");
	        }
		 
		 AddressEntity address = convertToAddressEntity(userRequest);
		 address = addressRepository.save(address);
		 
		 customer.setAddress_customer_id(address);
		 customer.setStatus(0);
		 RoleEntity role = roleRepository.findById(Long.parseLong("1")).get();
		 customer.setRole_customer(role);
		 userRepository.save(customer);
	}
	

    
	@Override
	public boolean updateStatusByEmail(String email) {
        CustomerEntity customer = userRepository.findByEmail(email).get();
        if (customer != null) {
            customer.setStatus(1);
            userRepository.save(customer);
            return true; 
        }
        return false; 
	}

	@Override
	public CustomerDTO Login(String email, String password) {
		// TODO Auto-generated method stub
		Optional<CustomerEntity> userOt = userRepository.findByEmailAndPassword(email, password);
		return userOt.map(user -> {
	        CustomerDTO userDTO = new CustomerDTO();
	        userDTO.setId(user.getId());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setStatus(user.getStatus());
	        return userDTO;
	    }).orElse(null); // Trả về null nếu không tìm thấy User
		
	}

	@Override
	public CustomerDTO Forgot(String email) {
		Optional<CustomerEntity> userOt = userRepository.findByEmail(email);
		return userOt.map(user -> {
	        CustomerDTO userDTO = new CustomerDTO();
	        userDTO.setId(user.getId());
	        userDTO.setEmail(user.getEmail());
	        userDTO.setStatus(user.getStatus());
	        return userDTO;
	    }).orElse(null); // Trả về null nếu không tìm thấy User
	}

	@Override
	public boolean ResetPass(String pass, String email) {
		Optional<CustomerEntity> userOt = userRepository.findByEmail(email);
		if (userOt.isPresent()) {
			CustomerEntity  cusEntity =  userOt.get();
			cusEntity.setPassword(pass);
			userRepository.save(cusEntity);
			return true;
		}
		else {
			return false;
		}
	}


}
