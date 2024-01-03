package com.kh.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.model.Product;
import com.kh.spring.service.ProductService;

// @Controller html 파일을 바라봄
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders="*")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/item")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProduct();
		return ResponseEntity.ok(products);
	}
	
	/*
	 	@PostMapping: 클라이언트(사용자)가 html에 작성한 정보를 DB에 저장할 수 있도록 도와주는 어노테이션
	 	ResponseEntity: 응답을 나타내는 클래스, 우리가 자주 봤던 404, 400, 500, 이외에 200이라는 응답이 있음
	 	200: 성공적으로 데이터 전송
	 	ResponseEntity.ok는 데이터를 잘 전송했다는 의미, 200이라는 내용을 담고 있음
	 */
	@PostMapping("/add")
	public ResponseEntity<Product> createProduct(@RequestBody Product newProduct){
		Product createdProduct = productService.insertProduct(newProduct);
		return ResponseEntity.ok(createdProduct);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productService.deleteById(id);
		return ResponseEntity.ok("성공적으로 삭제했습니다");
	}
}
