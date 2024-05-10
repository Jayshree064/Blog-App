package com.tatvasoft.BlogApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tatvasoft.BlogApp.dto.ResponseDto;
import com.tatvasoft.BlogApp.dto.TypeRequestDto;
import com.tatvasoft.BlogApp.entities.Subtype;
import com.tatvasoft.BlogApp.service.SubtypeService;

@RestController
@CrossOrigin
public class SubtypeController {

	@Autowired
	private SubtypeService subtypeService;
	
	@GetMapping("/subtype")
	public ResponseEntity<ResponseDto> getAllType(){
		try {			
			List<Subtype> response = subtypeService.getAllSubtypes();
			if(response.size()==0) {
				ResponseDto res = ResponseDto.builder().message("There is No Sub Types").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("All Sub Types").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(true).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	@PostMapping("/subtypes")
	public ResponseEntity<ResponseDto> getSubTypesByType(@RequestBody TypeRequestDto requestObj){
		try {
			List<Subtype> response = subtypeService.getSubTypesBasedOnType(requestObj.getId());
			if(response.size()==0) {
				ResponseDto res = ResponseDto.builder().message("There is No Sub Types related to type").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("All Sub Types related to type").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(true).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
}
