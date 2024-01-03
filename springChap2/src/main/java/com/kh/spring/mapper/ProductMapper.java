package com.kh.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.spring.model.Product;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM react_product")
    List<Product> getAllProduct();

    @Insert("INSERT INTO react_product (name, price, description) VALUES (#{name}, #{price}, #{description})")
    void insertProduct(Product product);

    @Delete("DELETE FROM react_product WHERE id = #{id}")
    void deleteProduct(Long id);

    @Update("UPDATE react_product SET name = #{name}, price = #{price}, description = #{description} WHERE id = #{id}")
    void updateProduct(Product product);
}