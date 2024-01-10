package com.spring.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverDTO {
	private String id;
	private String email;
	private String name;
	private String mobile;
	private String birthday;
	
}
