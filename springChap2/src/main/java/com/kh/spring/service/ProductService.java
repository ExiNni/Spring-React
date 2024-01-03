package com.kh.spring.service;

import java.util.List;
import java.util.Optional;

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

    public Product insertProduct(Product product) {
        productMapper.insertProduct(product);
        return product; // 추가된 Product 반환
    }

    public void deleteById(Long id) {
        productMapper.deleteProduct(id);
    }

    public Product save(Product product) {
        productMapper.updateProduct(product);
        return product; // 업데이트된 Product 반환
    }
}
