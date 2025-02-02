package com.tatvasoft.BlogApp.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tatvasoft.BlogApp.dto.BlogRequestDto;
import com.tatvasoft.BlogApp.entities.Blog;
import com.tatvasoft.BlogApp.entities.Subtype;
import com.tatvasoft.BlogApp.entities.Type;
import com.tatvasoft.BlogApp.entities.User;
import com.tatvasoft.BlogApp.mapper.BlogMapper;
import com.tatvasoft.BlogApp.repository.BlogRepo;
import com.tatvasoft.BlogApp.repository.SubtypeRepo;
import com.tatvasoft.BlogApp.repository.TypeRepo;
import com.tatvasoft.BlogApp.repository.UserRepo;
import com.tatvasoft.BlogApp.service.BlogService;


@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepo blogRepo;
	
	@Autowired
	private TypeRepo typeRepo;
	
	@Autowired
	private SubtypeRepo subtypeRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Override
	public List<Blog> getAllBlog(String status) {
		return blogRepo.findByDeletedDateIsNullAndStatusLike(status);
	}

	@Override
	public Blog getBlogById(long id) {
		return blogRepo.findByIdAndDeletedDateIsNull(id);
	}

	@Override
	public List<Blog> getBlogByUser(String email) {
		return blogRepo.findByAuthor_EmailAndDeletedDateIsNull(email);
	}

	@Override
	public void addBlog(BlogRequestDto blogData) throws Exception {
		try {
			if(blogData.getId() == null) {
				Blog blog = new Blog();
				blog.setTitle(blogData.getTitle());
				blog.setDescription(blogData.getDescription());
				blog.setContent(blogData.getContent());
				User author = userRepo.findByEmailAndDeletedDateIsNull(blogData.getUser());
				if(author == null) {
					throw new Exception("Author is not valid");
				}
				blog.setAuthor(author);
				Type type = typeRepo.findByIdAndDeletedDateIsNull(blogData.getTypeId());
				if(type == null) {
					throw new Exception("Type is not valid");
				}
				blog.setTypeId(type);
				Subtype subtype = subtypeRepo.findByIdAndDeletedDateIsNull(blogData.getSubtypeId());
				if(subtype == null) {
					throw new Exception("Subtype is not valid");
				}
				blog.setSubTypeId(subtype);
				blog.setCreatedDate(new Date());
				blog.setStatus(blogData.getStatus());
				blogRepo.save(blog);
			}else {
				Blog blog = blogRepo.findByIdAndDeletedDateIsNull(blogData.getId());
				if(blog != null) {
					blog.setTitle(blogData.getTitle());
					blog.setDescription(blogData.getDescription());
					blog.setContent(blogData.getContent());
					Type type = typeRepo.findByIdAndDeletedDateIsNull(blogData.getTypeId());
					blog.setTypeId(type);
					Subtype subtype = subtypeRepo.findByIdAndDeletedDateIsNull(blogData.getSubtypeId());
					blog.setSubTypeId(subtype);
					User author = userRepo.findByEmailAndDeletedDateIsNull(blogData.getUser());
					blog.setAuthor(author);
					blog.setCreatedDate(new Date());
					blog.setStatus(blogData.getStatus());
					blogRepo.save(blog);
				}else {
					throw new NullPointerException("Blog Not Found");
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
