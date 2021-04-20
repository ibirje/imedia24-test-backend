package com.imedia.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imedia.models.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductInsertRequest {

	
	private Product product;
	private String currency;
	
	
	
	public ProductInsertRequest() {
	}
	

	public ProductInsertRequest(Product product, String currency) {
		this.product = product;
		this.currency = currency;
	}

	
	public Product getProduct() {
		return product;
	}
	
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public String getCurrency() {
		return currency == null ? "EUR" : currency;
	}
	
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
