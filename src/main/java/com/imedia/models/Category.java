package com.imedia.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.imedia.utils.RegList;
import com.sun.istack.NotNull;

@Entity
@Table(name = "category")
public class Category implements Serializable{

	private static final long serialVersionUID = 89898L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private long id;

	@NotNull
	@Size(min = 3 , max = 255)
	@Pattern(regexp = RegList.REGEX_TITLE)
	private String name;
	
	@NotNull
	@Size(min = 3, max = 255)
	@Pattern(regexp = RegList.REGEX_IMAGE)
	private String image;
	
	private String link;


	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "parentcategory",insertable = true, updatable = true)
	private Long parentCategoryId;
	
	@ManyToOne
	@JoinColumn(name="parentcategory", referencedColumnName = "id", insertable = false, updatable = false)  
	Category parentcategory;

	
	public Category() {
		
	}
	
	public Category(String name2, String image2, String link2) {
		this.image = image2;
		this.name = name2;
		this.link = link2;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Category getParentcategory() {
		return parentcategory;
	}

	public void setParentcategory(Category parentcategory) {
		this.parentcategory = parentcategory;
	}
	
	
}