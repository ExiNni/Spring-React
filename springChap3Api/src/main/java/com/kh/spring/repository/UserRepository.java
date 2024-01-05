package com.kh.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.UserSNS;

public interface UserRepository extends JpaRepository<UserSNS, Long>{
	

}
