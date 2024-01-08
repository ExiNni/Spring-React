package com.kh.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.model.Member;

@Mapper
public interface MemberMapper {
	void insertMember(Member member);
}