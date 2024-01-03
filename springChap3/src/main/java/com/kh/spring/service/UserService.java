package com.kh.spring.service;

import com.kh.spring.model.UserGoogle;

public interface UserService {
	UserGoogle findByMyname(String myname);
	void saveUser(UserGoogle user);
}