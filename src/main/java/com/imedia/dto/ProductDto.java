package com.imedia.dto;

import com.imedia.models.Product;

public class ProductDto {


	private String name;
	private String ref;
	private String image;
	private Double price;
	private String description;
	private String categoryName;
	
	public ProductDto(String name, String ref, String image, Double price, String description) {
		this(name, ref, image, price, description, null);
	}
	
	public ProductDto(String name, String ref, String image, Double price, String description, String category) {
		this.name = name;
		this.ref = ref;
		this.image = image;
		this.price = price;
		this.description = description;
		this.categoryName = category;
	}

	public ProductDto() {
		
	}
	
	
	public ProductDto(Product p) {
		this.name = p.getName();
		this.ref = p.getRef();
		this.image = p.getImage();
		this.price = p.getPrice();
		this.description = p.getDescription();
		this.categoryName = p.getCategoryName();
	}
	

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductDto [name=" + name + ", ref=" + ref + ", image=" + image + ", price=" + price + ", description="
				+ description + ", categoryName=" + categoryName + "]";
	}
	
	
	
}
