package com.program.ecommerce.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.program.ecommerce.dto.ProductDTO;
import com.program.ecommerce.entity.Product;
import com.program.ecommerce.repositories.ProductRepository;
import com.program.ecommerce.services.exceptions.DataBaseException;
import com.program.ecommerce.services.exceptions.ResourceNotFoundException;

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
		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado"));
		return new ProductDTO(product);
		
		
	}
	
	public ProductDTO insert(ProductDTO dto) {		
		Product entity = new Product();
		copyDtoToEntity(dto, entity);		
		entity = productRepository.save(entity);		
		return new ProductDTO(entity);	
		
		
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		
		try {
			Product entity = productRepository.getReferenceById(id);		
		    copyDtoToEntity(dto, entity);		
		    entity = productRepository.save(entity);		
		    return new ProductDTO(entity);	
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");
		}
		
		
				
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		
		try {			
			productRepository.deleteById(id);
		
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");
		}
		
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
			
		}
	}
	


	@Transactional
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
	
		
}


