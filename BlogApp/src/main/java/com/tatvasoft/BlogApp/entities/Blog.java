package com.tatvasoft.BlogApp.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="blog")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="blog_id")
	private long id;
	
	private String title;
		
	private String description;
	
	private String content;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="type")
	private Type typeId;
	
	@ManyToOne
	@JoinColumn(name="sub_type")
	private Subtype subTypeId;
	
	@ManyToOne
	@JoinColumn(name="author")
	private User author;
	
	@Column(name = "blog_image")
	private String blogImage;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Column(name="deleted_date")
	private Date deletedDate;
}
