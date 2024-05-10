package com.tatvasoft.BlogApp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvasoft.BlogApp.entities.Type;
import com.tatvasoft.BlogApp.repository.TypeRepo;
import com.tatvasoft.BlogApp.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService{

	@Autowired
	private TypeRepo typeRepo;
	
	@Override
	public List<Type> getAllTypes() {
		return typeRepo.findByDeletedDateIsNull();
	}

}
