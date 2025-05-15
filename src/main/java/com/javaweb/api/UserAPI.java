package com.javaweb.api;

import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.LoginRequest;
import com.javaweb.beans.request.UserRequest;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.customeExceptions.FiledRequiredException;
import com.javaweb.service.CustomerService;
import com.javaweb.service.UserService;
import com.javaweb.util.EmailService;
import com.javaweb.util.OTPGenerate;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;


@RestController
@RequestMapping("/api/auth")
public class UserAPI {
    @Autowired
    private UserService userService;
    
    @Autowired
    private CustomerService customerService;
    
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    
    @PostMapping("/register")
    public ResultDTO register(@RequestBody UserRequest request) {
    	return customerService.registerUser(request);   	
    }

    @PostMapping("/verifyOtp")
    public ResultDTO verifyOtp(@RequestBody UserRequest request) {
    	String savedOtp = redisTemplate.opsForValue().get(request.getEmail());
    	ResultDTO result = new ResultDTO();
    	if(request.getOtp() != null && !request.getOtp().equals("") && request.getOtp().equals(savedOtp)) {
    		result = customerService.saveUser(request);   		
    	} else {
    		result.setStatus(false);
    		result.setMessage("OTP không hợp lệ hoặc đã hết hạn");
    	}
    	return result;
	} 
    
    @PostMapping(value = "/loginAdmin")
    public Object loginAdmin(@RequestBody @Valid LoginRequest loginRequest) throws KeyLengthException, JOSEException {	
    	String email = loginRequest.getEmail();
    	String password = loginRequest.getPassword();
    	ResultDTO<String> u = customerService.LoginAdmin(email,password) ;
        return u ; 
    }
    
}

