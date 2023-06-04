package com.program.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.program.ecommerce.entity.Product;

public class ProductDTO {
	
	
	private Long id;
	
	@Size(min = 3, max=80, message = "Nome etre 3 e 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;	
	
	@Size(min = 10, message = "Descrição minimo 10 caracteres")
	@NotBlank(message = "Campo requerido")
	private String description;
	
	@Positive(message = "O preço deve ser positivo")
	private Double price;
	private String imgUrl;
	
	
	public ProductDTO() {
		
	}


	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {		
		id = entity.getId();
		name = entity.getName();
		description =entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
