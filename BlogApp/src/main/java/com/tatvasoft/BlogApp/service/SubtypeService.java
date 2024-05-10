package com.tatvasoft.BlogApp.service;

import java.util.List;

import com.tatvasoft.BlogApp.entities.Subtype;

public interface SubtypeService {

	List<Subtype> getAllSubtypes();
	
	List<Subtype> getSubTypesBasedOnType(Long id);
}
