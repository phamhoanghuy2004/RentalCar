package com.javaweb.service;

import java.text.ParseException;
import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.GGLoginRequest;
import com.javaweb.beans.request.updateProfileRequest;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.javaweb.beans.request.UserRequest;


public interface CustomerService {
	public ResultDTO saveUser(UserRequest request);
	public ResultDTO registerUser(UserRequest userRequest);
	public ResultDTO<String> Login (String email, String password) throws KeyLengthException, JOSEException; // String la token do
	public ResultDTO<String> LoginGG (GGLoginRequest ggLoginRequest) throws KeyLengthException, JOSEException;
	public ResultDTO<CustomerDTO> Forgot (String email);
	public boolean ResetPass (String pass, String email);
	public boolean updateStatusByEmail(String email);
	public CustomerDTO getProfile(String token) throws JOSEException, ParseException;
	public ResultDTO updateProfile (updateProfileRequest request, String token) throws JOSEException, ParseException;
}
