package com.javaweb.service;

import com.javaweb.beans.CustomerDTO;

public interface UserService {
	public CustomerDTO Login (String email, String password);
	public CustomerDTO Forgot (String email);
	public boolean ResetPass (String pass, String email);
	public boolean updateStatusByEmail(String email);
	
}
