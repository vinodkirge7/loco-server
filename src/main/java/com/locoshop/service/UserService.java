package com.locoshop.service;

import com.locoshop.models.User;

public interface UserService {
	
    public User findUserByEmail(String email);
	
	
	public User getUserByToken(String jwt);

}
