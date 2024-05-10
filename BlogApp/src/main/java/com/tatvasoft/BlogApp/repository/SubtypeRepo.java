package com.tatvasoft.BlogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatvasoft.BlogApp.entities.Subtype;

public interface SubtypeRepo extends JpaRepository<Subtype, Long>{

	List<Subtype> findByDeletedDateIsNull();
	
	List<Subtype> findByTypeId_IdAndDeletedDateIsNull(Long id);

	Subtype findByIdAndDeletedDateIsNull(Long id);
}
