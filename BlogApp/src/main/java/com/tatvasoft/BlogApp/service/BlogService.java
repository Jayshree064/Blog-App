package com.tatvasoft.BlogApp.service;

import java.util.List;

import com.tatvasoft.BlogApp.dto.BlogRequestDto;
import com.tatvasoft.BlogApp.entities.Blog;

public interface BlogService {
	
	List<Blog> getAllBlog(String status);
	
	Blog getBlogById(long id);
	
	List<Blog> getBlogByUser(String email);
	
	void addBlog(BlogRequestDto blogData) throws Exception;
}
