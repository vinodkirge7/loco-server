package com.locoshop.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locoshop.config.JwtProvider;
import com.locoshop.models.User;
import com.locoshop.repository.UserRepository;
import com.locoshop.response.AuthResponse;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private JwtProvider jwtProvider;

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private com.locoshop.service.CustomUserDetailService customUserDetailService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<com.locoshop.response.AuthResponse> CreateUserHandler(@RequestBody User user) throws Exception{
		
		User isEmailExist = userRepository.findByEmail(user.getEmail());
		if(isEmailExist!= null) {
			
			throw new Exception("Email is already used");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		String jwt = jwtProvider.generateTokenString(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Register Success");
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
	}

	
	
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) throws Exception{
		
		String username = req.getEmail();
		String password = req.getPassword();
		
		Authentication authentication=authenticate(username,password);
	
		String jwt = jwtProvider.generateTokenString(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("Login Success");
		
		
		
		return new ResponseEntity<>(authResponse, HttpStatus.OK);
	}



	private Authentication authenticate(String username, String password) throws Exception {
	
		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new Exception("Invalid username");
			
			
		}
		

		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			
			throw new Exception("Invalid password");
			
		}
		
		
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
