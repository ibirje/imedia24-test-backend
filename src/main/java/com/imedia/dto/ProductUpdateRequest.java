package com.imedia.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.imedia.models.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductUpdateRequest {



	
	private Product product;
	private String currency;
	private String ref;
	
	
	

	public ProductUpdateRequest() {
	}
	

	public ProductUpdateRequest(Product product, String ref) {
		this.product = product;
		this.ref = ref;
	}

	
	
	public ProductUpdateRequest(Product product, String currency, String ref) {
		super();
		this.product = product;
		this.currency = currency;
		this.ref = ref;
	}

	
	

	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
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
