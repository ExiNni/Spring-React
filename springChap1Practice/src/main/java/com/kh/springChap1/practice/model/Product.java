package com.kh.springChap1.practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ReactProduct")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reactproduct_seq")
	@SequenceGenerator(name = "reactproduct_seq", sequenceName = "reactproduct_seq", allocationSize = 1)
	private Long id;
	private String name;
	private double price;
	private String description;
}
