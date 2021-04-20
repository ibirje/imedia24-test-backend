package com.imedia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.imedia.utils.RegList;
import com.imedia.utils.Validator;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 255)
	@Pattern(regexp = RegList.REGEX_PRODUCT_REF)
	private String ref;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long categoryid;

	
	@NotNull
	@Size(min = 3, max = 255)
	@Pattern(regexp = RegList.REGEX_TITLE)
	private String name;

	
	@NotNull
	@Size(min = 3, max = 255)
	private String image;

	@NotNull
	@Min(value = 0)
	private Double price;

	@NotNull
	private Boolean enabled;
	
	private String description;

	@ManyToOne
	@JoinColumn(name="categoryid", referencedColumnName = "id", insertable = false, updatable = false)    
    protected Category category;
	
	@Transient
	protected String categoryName;
	
	
	public Product() {
	}
	
	public Product(String name, String ref, String image, Double price, String description) {
		super();
		this.name = name;
		this.ref = ref;
		this.image = image;
		this.price = price;
		this.description = description;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
	
	public String getCategoryName() {
		
		if(categoryName != null) 
			return categoryName;
		if(category == null) 
			return null;
		return category.getName();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", ref=" + ref + ", image=" + image + ", price=" + price
				+ ", enabled=" + enabled + ", description=" + description + ", category=" + category + "]";
	}

	
	public void copy(Product p) {

		if (p == null)
			return;
		
		Validator v = new Validator();
		
		if (!v.isNullOrEmpty(p.getName()))
			setName(p.getName());

		if (!v.isNullOrEmpty(p.getImage()))
			setImage(p.getImage());

		if (p.getPrice() != null && p.getPrice() > 0)
			setPrice(p.getPrice());
		
		if (p.getEnabled() != null)
			setEnabled(p.getEnabled());

		if (!v.isNullOrEmpty(p.getCategoryName()) && !getCategoryName().equals(getCategoryName()) )
			setCategoryName(p.getCategoryName());
	}
	
	
	
}
