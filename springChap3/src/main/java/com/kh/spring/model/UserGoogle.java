package com.kh.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_google")
@Getter
@Setter
public class UserGoogle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "myname")
	private String myname;
	
	@Column(name = "email")
	private String email;
	
	public String getMyname() {
        return this.myname;
    }
	
	
}