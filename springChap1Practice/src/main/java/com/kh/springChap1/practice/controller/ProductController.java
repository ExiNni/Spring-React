package com.kh.springChap1.practice.controller;

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

import com.kh.springChap1.practice.model.Product;
import com.kh.springChap1.practice.repository.ProductRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders="*")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/item")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productRepository.findAll();
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
		Product createdProduct = productRepository.save(newProduct);
		return ResponseEntity.ok(createdProduct);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		productRepository.deleteById(id);
		return ResponseEntity.ok("성공적으로 삭제했습니다");
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct){
		Product existProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디를 찾을 수 없음" + id));
		existProduct.setName(updatedProduct.getName());
		existProduct.setPrice(updatedProduct.getPrice());
		existProduct.setDescription(updatedProduct.getDescription());
		
		Product updateProduct = productRepository.save(existProduct);
		return ResponseEntity.ok(updateProduct);
	}
}
