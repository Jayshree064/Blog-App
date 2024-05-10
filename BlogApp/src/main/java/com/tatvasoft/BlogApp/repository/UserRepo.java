package com.tatvasoft.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatvasoft.BlogApp.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
	User findByEmailAndDeletedDateIsNull(String email);
	
}
