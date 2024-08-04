package com.locoshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.locoshop.models.User;
import com.locoshop.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/user/profile")
	public ResponseEntity<User> findUserByJwt(@RequestHeader("Authorization") String jwt){
		
		 User user = userService.getUserByToken(jwt);
		 
		return new ResponseEntity<>(user,HttpStatus.OK);
	}

}
