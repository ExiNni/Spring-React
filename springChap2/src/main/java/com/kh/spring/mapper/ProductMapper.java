package com.kh.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.model.Product;

@Mapper
public interface ProductMapper {
	// src/main/resources/mapper/ProductMapper.xml
	// 밑에 작성해준 sql id만 작성할 것
	// JPA처럼 mapper.xml 파일에는 작성하지 않은 sql문과 id에 관련된 메서드를 모두 작성해주면 에러남
	List<Product> getAllProduct();
}
