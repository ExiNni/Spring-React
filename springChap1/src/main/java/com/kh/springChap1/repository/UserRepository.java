package com.kh.springChap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springChap1.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	// 추가적으로 필요한 메서드만 작성
}
