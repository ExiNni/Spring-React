package com.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.model.UserGoogle;
import com.kh.spring.repository.UserGoogleRepository;

@Service
public class UserServiceImpl implements UserService {
	private final UserGoogleRepository userGoogleRepository;
	
	@Autowired
	public UserServiceImpl(UserGoogleRepository userGoogleRepository) {
		this.userGoogleRepository = userGoogleRepository;
	}
	
	@Override
	public UserGoogle findByMyname(String myname) {
		return userGoogleRepository.findByMyname(myname);
	}
	
	@Override
	public void saveUser(UserGoogle user) {
		userGoogleRepository.save(user);
	}
}