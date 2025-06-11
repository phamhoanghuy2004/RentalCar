package com.javaweb.api;

import java.text.ParseException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.ForgotPasswordRequest;
import com.javaweb.beans.request.GGLoginRequest;
import com.javaweb.beans.request.LoginRequest;
import com.javaweb.beans.request.ResetPasswordRequest;
import com.javaweb.beans.request.VerifyOTPRequest;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.service.CustomerService;
import com.javaweb.util.EmailService;
import com.javaweb.util.OTPGenerate;
import com.javaweb.util.TokenService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "CustomerAuth API", description = "Các API liên quan đến xác thực khách hàng")
public class CustomerAuthAPI {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OTPGenerate  otpGenerate;
    
    @Operation(
			summary = "Đăng nhập khách hàng, cần có email và password"
		)
    @PostMapping(value = "/login")
    public Object login(@RequestBody @Valid LoginRequest loginRequest) throws KeyLengthException, JOSEException {	
    	String email = loginRequest.getEmail();
    	String password = loginRequest.getPassword();
    	ResultDTO<String> u = customerService.Login(email,password) ;
        return u ; 
    }
    
    @Operation(
			summary = "Đăng nhập bằng google"
		)
    @PostMapping(value = "/ggLogin")
    public Object loginGG (@RequestBody @Valid GGLoginRequest ggLoginRequest) throws KeyLengthException, JOSEException {
    	ResultDTO<String> u = customerService.LoginGG(ggLoginRequest) ;
    	return u;
    }
    
    
    @Operation(
			summary = "Quên mật khẩu, cần có email để xác thực"
		)
    @PostMapping(value = "/forgot")
   	public Object forgot(@RequestBody @Valid ForgotPasswordRequest forgotRequest) {
   	    	String email = forgotRequest.getEmail();
   	    	ResultDTO<CustomerDTO> result = customerService.Forgot(email);
   	    	if (result.isStatus()) {
   	    		String otp = otpGenerate.generateOTP(email);
   	   	    	EmailService.sendOTP(email, otp);
   	    	}
   	        return result; 
   	    }
    
    @Operation(
			summary = "Xác thực OTP, cần có opt và email để xác thực"
		)
    @PostMapping(value="/verify-otp")
    public Object OTPCheck (@RequestBody @Valid VerifyOTPRequest verifyOTPRequest) {
    	
    	String otp = verifyOTPRequest.getOtp();
    	String email = verifyOTPRequest.getEmail();
    	ResultDTO result = new ResultDTO();
    	
    	if (otpGenerate.verifyOTP(email, otp,"verify")) {
    		result.setStatus(true);
        	result.setMessage("OTP thành công");
    	}
        else {
        	result.setStatus(false);
        	result.setMessage("OTP sai hoặc hết hạn");
        }   
        return result;
    }
    
    @Operation(
			summary = "Đặt lại mật khẩu, cần có otp, email, và mật khẩu mới"
		)
    @PostMapping(value = "/reset")
    public Object ResetPass (@RequestBody @Valid ResetPasswordRequest resetRequest) {
    	String newPass = resetRequest.getNewPassword();
    	String email = resetRequest.getEmail();
    	String otp = resetRequest.getOtp();
    	ResultDTO result = new ResultDTO();
    	
    	if (otpGenerate.verifyOTP(email, otp,"reset") && customerService.ResetPass(newPass, email)) {
    		result.setStatus(true);
        	result.setMessage("Cập nhật thành công mật khẩu");
    	}
    	else {
    		result.setStatus(false);
        	result.setMessage("Lỗi không thể cập nhật mật khẩu");
    	}
    	return result;
    }
    
    
}

