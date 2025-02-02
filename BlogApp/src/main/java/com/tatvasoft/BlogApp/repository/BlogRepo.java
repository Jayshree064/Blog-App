package com.tatvasoft.BlogApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatvasoft.BlogApp.entities.Blog;

public interface BlogRepo extends JpaRepository<Blog, Long>{
	
	List<Blog> findByDeletedDateIsNullAndStatusLike(String status);
	
	Blog findByIdAndDeletedDateIsNull(long id);
	
	List<Blog> findByAuthor_EmailAndDeletedDateIsNull(String email);
	
}
