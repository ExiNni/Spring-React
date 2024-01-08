package com.kh.spring.service;

import java.util.List;

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
    
    public List<Member> getAllMembers() {
        return memberMapper.selectAllMembers();
    }
    
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    public Member getMemberById(long id) {
        return memberMapper.getMemberById(id);
    }
    
    public void deleteMember(long id) {
        memberMapper.deleteMember(id);
    }

    
    
}
