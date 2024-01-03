package com.kh.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.mapper.ProductMapper;
import com.kh.spring.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> getAllProduct() {
		return productMapper.getAllProduct();
	}
}
