package com.imedia.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imedia.models.Category;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductsByCategoryResponse {

	private Category category;
	private List<ProductDto> products;


	public ProductsByCategoryResponse(Category category, List<ProductDto> products) {
		this.category = category;
		this.products = products;
	}

	public ProductsByCategoryResponse() {
	}
	
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public List<ProductDto> getProducts() {
		return products;
	}
	
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
}
