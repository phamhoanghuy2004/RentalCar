package com.javaweb.service;

import java.util.Optional;

import com.javaweb.beans.CustomerDTO;
import com.javaweb.beans.UserRequest;


public interface UserService {
	

	public CustomerDTO Login (String email, String password);
	public CustomerDTO Forgot (String email);
	public boolean ResetPass (String pass, String email);
	public void registerUser(UserRequest userRequest);
	public boolean updateStatusByEmail(String email);
	
}
