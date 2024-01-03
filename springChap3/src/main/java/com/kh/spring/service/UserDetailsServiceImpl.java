package com.kh.spring.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.spring.model.UserGoogle;
import com.kh.spring.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String myname) throws UsernameNotFoundException {
        UserGoogle user = userRepository.findByMyname(myname)
            .orElseThrow(() -> new UsernameNotFoundException("해당 이름으로 유저를 찾을 수 없습니다." + myname));
        
        return new org.springframework.security.core.userdetails.User(
                user.getMyname(), // Assuming this is the username field
                "",
                Collections.emptyList());
    }
}
