package com.tatvasoft.BlogApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResponseDto {

	private String message;
	private int statusCode;
	private boolean status;
}
