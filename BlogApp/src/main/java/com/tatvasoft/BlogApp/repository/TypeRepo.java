package com.tatvasoft.BlogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatvasoft.BlogApp.entities.Type;

public interface TypeRepo extends JpaRepository<Type, Long>{

	List<Type> findByDeletedDateIsNull();
	
	Type findByIdAndDeletedDateIsNull(Long id);
}
