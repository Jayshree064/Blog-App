package com.tatvasoft.BlogApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvasoft.BlogApp.entities.Subtype;
import com.tatvasoft.BlogApp.repository.SubtypeRepo;
import com.tatvasoft.BlogApp.service.SubtypeService;

@Service
public class SubtypeServiceImpl implements SubtypeService{

	@Autowired
	private SubtypeRepo subtypeRepo;
	
	@Override
	public List<Subtype> getAllSubtypes() {
		return subtypeRepo.findByDeletedDateIsNull();
	}
	
	@Override
	public List<Subtype> getSubTypesBasedOnType(Long id) {
		return subtypeRepo.findByTypeId_IdAndDeletedDateIsNull(id);
	}

}
