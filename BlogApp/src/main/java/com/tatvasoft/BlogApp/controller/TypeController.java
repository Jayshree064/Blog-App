package com.tatvasoft.BlogApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tatvasoft.BlogApp.dto.ResponseDto;
import com.tatvasoft.BlogApp.entities.Blog;
import com.tatvasoft.BlogApp.entities.Type;
import com.tatvasoft.BlogApp.service.TypeService;

@RestController
@CrossOrigin
public class TypeController {
	
	@Autowired
	private TypeService typeService;

	@GetMapping("/type")
	public ResponseEntity<ResponseDto> getAllType(){
		try {			
			List<Type> response = typeService.getAllTypes();
			if(response.size()==0) {
				ResponseDto res = ResponseDto.builder().message("There is No Types").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("All Types").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(true).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
}
