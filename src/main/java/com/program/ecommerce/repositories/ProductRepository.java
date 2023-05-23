package com.program.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.program.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {

}
