package com.tatvasoft.BlogApp.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogRequestDto {

	private Long id;
	private String title;
	private String description;
	private String content;
	private Long typeId;
	private Long subtypeId;
	private String user;
	private String status;
}
