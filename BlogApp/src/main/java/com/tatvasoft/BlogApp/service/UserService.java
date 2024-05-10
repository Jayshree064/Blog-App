package com.tatvasoft.BlogApp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tatvasoft.BlogApp.entities.User;

@Service
public interface UserService extends UserDetailsService{

	UserDetails loadUserByUsername(String email);
	
	void saveUser(User user);
	
	User getUserData(String email);
}
