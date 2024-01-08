package com.kh.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.model.Member;

@Mapper
public interface MemberMapper {
	void insertMember(Member member);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(long id);
	Member getMemberById(long id);
}