package com.javaweb.service;

import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.request.UserRequest;


public interface CustomerService {
	public ResultDTO saveUser(UserRequest request);
	public ResultDTO registerUser(UserRequest userRequest);
}
