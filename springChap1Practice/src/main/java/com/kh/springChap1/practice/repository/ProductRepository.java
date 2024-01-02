package com.kh.springChap1.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.springChap1.practice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
