package com.kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.model.Product;
import com.kh.spring.service.ProductService;

// @Controller html 파일을 바라봄
@RestController // api로 전달받을 수 있게 해주는 컨트롤러
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders="*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/item")
	public ResponseEntity<List<Product>> getAllProduct(){
		List<Product> products = productService.getAllProduct();
		return ResponseEntity.ok(products);
	}
}
