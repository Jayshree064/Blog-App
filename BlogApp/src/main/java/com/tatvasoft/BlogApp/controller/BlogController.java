package com.tatvasoft.BlogApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tatvasoft.BlogApp.dto.BlogRequestDto;
import com.tatvasoft.BlogApp.dto.ResponseDto;
import com.tatvasoft.BlogApp.entities.Blog;
import com.tatvasoft.BlogApp.enums.BlogStatusEnum;
import com.tatvasoft.BlogApp.service.BlogService;

@RestController
@CrossOrigin
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public ResponseEntity<ResponseDto> allBlogs(){
		try {			
			List<Blog> response = blogService.getAllBlog(BlogStatusEnum.Published.toString());
			if(response.size()==0) {
				ResponseDto res = ResponseDto.builder().message("There is No Blog").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("All blogs").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(false).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	@PostMapping("/blog/{id}")
	public ResponseEntity<ResponseDto> getBlockById(@PathVariable("id") long id){
		try {			
			Blog response = blogService.getBlogById(id);
			if(response == null) {
				ResponseDto res = ResponseDto.builder().message("There is No Blog").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("Get Blog by id").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(false).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	@PostMapping("/blog")
	public ResponseEntity<ResponseDto> getBlockByUser(@RequestBody Map<String, Object> requestObj){
		String email = (String) requestObj.get("email");
		try {			
			List<Blog> response = blogService.getBlogByUser(email);
			if(response.size()==0) {
				ResponseDto res = ResponseDto.builder().message("There is No Blog").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
			else {
				ResponseDto res = ResponseDto.builder().message("All blogs of User").status(true).data(response).build();
				return ResponseEntity.status(HttpStatus.OK).body(res); 
			}
		}catch (Exception e) {
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(false).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
	@PostMapping("/add-blog")
	public ResponseEntity<ResponseDto> addBlog(@RequestBody BlogRequestDto blogData){
		System.out.println(blogData);
		try {
			blogService.addBlog(blogData);
			ResponseDto res = ResponseDto.builder().message("New Blog Added").status(true).data("").build();
			return ResponseEntity.status(HttpStatus.OK).body(res);
		}catch (Exception e) {
			System.out.println(e);
			ResponseDto res = ResponseDto.builder().message("Internal Server Error").status(false).data(new ArrayList<>()).build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}
	
}
