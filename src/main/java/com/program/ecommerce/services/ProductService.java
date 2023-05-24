package com.program.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.ecommerce.dto.ProductDTO;
import com.program.ecommerce.entity.Product;
import com.program.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = productRepository.findAll(pageable);
		return result.map(x -> new ProductDTO(x));
		
	}
	

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {		
		Product product = productRepository.findById(id).get();
		return new ProductDTO(product);
		
		
	}

}
