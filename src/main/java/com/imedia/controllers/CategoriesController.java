package com.imedia.controllers;

import javax.servlet.ServletException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imedia.config.Routes;
import com.imedia.dto.ProductsByCategoryResponse;
import com.imedia.models.Category;
import com.imedia.models.Product;
import com.imedia.services.CategoryService;
import com.imedia.services.ProductService;

@RestController
@RequestMapping(Routes.CATEGORIES)
@CrossOrigin(origins = "*")
public class CategoriesController {

	@Autowired
	CategoryService service;

	@Autowired
	ProductService productservice;
	
	
	/**
	 * 
	 * @return all available categories
	 */
	@GetMapping(Routes.LIST)
	public Iterable<Category> getList() {
		return service.getAll();
	}

	
	/**
	 * 
	 * @param category
	 * @return category by name
	 */
	@GetMapping({"","/{name}"})
	public ProductsByCategoryResponse getProductsByCategory( @PathVariable(value="name", required = false) String categ ) {
		
		return productservice.findByCategoryName(categ);
	}
	

	
	/**
	 * 
	 * insert product
	 * @param product
	 * @return
	 * @throws ServletException
	 */
	@PostMapping
	public Product add( @RequestBody @NotNull Category categ) throws ServletException{
		
		if(categ == null) return null;  // throw servletexception not found or nullpointerexception
		return service.add(categ);
	}

	
	
	/**
	 * update product
	 * @param product
	 * @param ref
	 * @return
	 * @throws ServletException
	 */
	@PutMapping
	public Product update(@RequestBody @NotNull Category categ) throws ServletException{
		return service.update(categ);
	}

	
	
	/**
	 * delete product
	 * @param ref
	 * @return
	 */
	@DeleteMapping
	public Product delete(@PathVariable @NotNull String name) {
		return service.delete(name);
	}
	
	
}
