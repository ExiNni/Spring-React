package com.kh.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.mapper.MemberMapper;
import com.kh.spring.model.Member;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;
    
    public void signUpMember(Member member) {
        memberMapper.insertMember(member);
    }
}
