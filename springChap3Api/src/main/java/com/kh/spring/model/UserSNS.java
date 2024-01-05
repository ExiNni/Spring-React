package com.kh.spring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserSNS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sns_seq")
	@SequenceGenerator(name = "users_sns_seq", sequenceName = "users_sns_seq", allocationSize = 1)
	private Long id;
	
	String name;
	private String email;
	private String provider;
	
}

//login/oauth2/code/naver
