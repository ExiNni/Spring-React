package com.kh.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.UserGoogle;

public interface UserGoogleRepository extends JpaRepository<UserGoogle, Long> {
	UserGoogle findByMyname(String myname);
}