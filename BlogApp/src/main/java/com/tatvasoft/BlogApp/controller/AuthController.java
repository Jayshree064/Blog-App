package com.tatvasoft.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tatvasoft.BlogApp.dto.LoginRequestDto;
import com.tatvasoft.BlogApp.dto.LoginResponseDto;
import com.tatvasoft.BlogApp.dto.CommonResponseDto;
import com.tatvasoft.BlogApp.entities.User;
import com.tatvasoft.BlogApp.serviceImpl.UserServiceImpl;
import com.tatvasoft.BlogApp.utill.JWTUtill;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private JWTUtill jwtUtil;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/register")
	public ResponseEntity<CommonResponseDto> registerUser(@RequestBody User user){
		userService.saveUser(user);
		CommonResponseDto response = CommonResponseDto.builder().message("User registered successfully !!!!").status(true).statusCode(200).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		}catch(BadCredentialsException ex) {
			LoginResponseDto response = LoginResponseDto.builder().message("Invalid username and password").status(false).token("").build();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}
		UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
		User user = userService.getUserData(request.getEmail());
        String token = jwtUtil.generateToken(user);
        LoginResponseDto response = LoginResponseDto.builder().message("Login Success...").token(token).status(true).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
}
