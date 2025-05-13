package com.javaweb.service.Impl;

import java.time.Duration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.UserRequest;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.util.EmailService;
import com.javaweb.util.OTPGenerate;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressEntity convertToAddressEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, AddressEntity.class);
    }
	
	public CustomerEntity convertToCustomerEntity(UserRequest userRequest) {
        return modelMapper.map(userRequest, CustomerEntity.class);
    }

	
	@Override
	public ResultDTO registerUser(UserRequest userRequest) {
		ResultDTO result = new ResultDTO();
	    if (userRequest == null || userRequest.getEmail() == null || userRequest.getEmail().isBlank()) {
	        result.setStatus(false);
	        result.setMessage("Email không được để trống");
	        return result;
	    }
		if (customerRepository.findByEmail(userRequest.getEmail()).isPresent()) {	            
	            result.setStatus(false);
	            result.setMessage("Email đã tồn tại trong hệ thống");
	    } else {	    	 
	    	String otp = OTPGenerate.generateOTP();
	    	try {
	    		   EmailService.sendOTP(userRequest.getEmail(), otp);
	    		   redisTemplate.opsForValue().set(userRequest.getEmail(), otp, Duration.ofMinutes(5));
	    		   result.setStatus(true);
	    		   result.setMessage("Đã gửi OTP. Vui lòng kiểm tra email của bạn");
	    	} catch (Exception e) {
	    		   result.setStatus(false);
	    		   result.setMessage("Không thể gửi OTP. Vui lòng thử lại sau");
	    		   e.printStackTrace();
	    		}
	     }
		 return result;
	}
	
	@Override
	public ResultDTO saveUser(UserRequest request) {
		ResultDTO result = new ResultDTO();
		try {
			AddressEntity newAddress = convertToAddressEntity(request);
			CustomerEntity customer = convertToCustomerEntity(request);

			String password = customer.getPassword();
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String encodedPassword = encoder.encode(password);
	        customer.setPassword(encodedPassword);
			
			customer.setCustomerAddress(newAddress);
			customerRepository.save(customer);
			result.setStatus(true);
			result.setMessage("Đăng ký thành công");
		} catch (Exception e) {
			result.setStatus(false);
			result.setMessage("Xảy ra lỗi trong quá trình lưu dữ liệu");
			e.printStackTrace();
		}
		return result;
	}
}
