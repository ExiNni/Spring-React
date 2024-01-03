package com.kh.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring.model.UserGoogle;

public interface UserRepository extends JpaRepository<UserGoogle, Long> {
	Optional<UserGoogle> findByMyname(String myname);
}