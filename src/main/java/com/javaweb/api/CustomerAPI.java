package com.javaweb.api;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.BookingRequest;
import com.javaweb.beans.request.updateProfileRequest;
import com.javaweb.service.CustomerService;
import com.nimbusds.jose.JOSEException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Customer API", description = "Các API liên quan đến khách hàng")
public class CustomerAPI {
	@Autowired
    private CustomerService customerService;
	
	@Operation(
				summary = "Lấy thông tin khách hàng có xác thực JWT, cần có token bằng cách đăng nhập"
			)
	@GetMapping(value = "/profile")
	public CustomerDTO getProfile (@RequestHeader (value = "Authorization", required = false) String token) throws JOSEException, ParseException {
		return customerService.getProfile(token);
	}
	
	@Operation(
			summary = "Cập nhật thông tin khách hàng có xác thực JWT, cần có token bằng cách đăng nhập"
		)
	@PostMapping("/updateProfile")
	    public Object bookingCar (@RequestBody @Valid updateProfileRequest request , @RequestHeader (value = "Authorization", required = false ) String token) throws JOSEException, ParseException {
	    	ResultDTO result = customerService.updateProfile(request,token);
	    	return result;
	 }
}
