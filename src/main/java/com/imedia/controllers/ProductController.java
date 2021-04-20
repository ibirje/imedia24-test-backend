package com.imedia.controllers;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imedia.config.Routes;
import com.imedia.dto.ListResponse;
import com.imedia.dto.ProductDto;
import com.imedia.dto.ProductInsertRequest;
import com.imedia.dto.ProductUpdateRequest;
import com.imedia.models.Product;
import com.imedia.services.ProductService;

@RestController
@RequestMapping(Routes.PRODUCTS)
@CrossOrigin(origins = "*")
public class ProductController {

	
	@Autowired
	private ProductService service;
	
	
	
	/** get method does full text search if a product title was provided 
	 * 
	 * @param title is the text that the search is applied on
	 * @param orderby is the sql orderby parameters ( ex : 'id desc' or 'price asc'). used for sorting
	 * @param size max page or query results size
	 * @param page page number used to return results between page*size and (page+1)*size
	 * 
	 * @return list of 
	 * {@link ListResponse}<{@link ProductDto}>
	 * 
	 * */
	@GetMapping
	public ListResponse<ProductDto> list (
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String orderby ,
			@RequestParam(required = false) Integer size ,
			@RequestParam(required = false) Integer page) throws ServletException {

		return service.listSearch(title, orderby, size, page); 
	}
	
	
	
	@GetMapping(Routes.DETAILS + "/{title}")
	public Product details(@PathVariable("title") String title) throws ServletException {

		return service.findByName(title); 
	}

	/**
	 * 
	 * insert product
	 * @param product
	 * @return
	 * @throws ServletException
	 */
	@PostMapping
	public Product add( @RequestBody ProductInsertRequest productins) 
				throws ServletException{

		return service.add(productins);
	}

	
	/**
	 * 
	 * @param ProductUpdateRequest
	 * @return
	 * @throws ServletException
	 */
	@PutMapping
	public Product update(@RequestBody ProductUpdateRequest p) throws ServletException{
		
		if(p == null || p.getProduct() == null 
				|| (p.getRef() == null && p.getProduct().getRef() == null)) 
			    	return null;
		
		if(p.getRef() == null) p.setRef(p.getProduct().getRef());
		
		return service.update(p);
	}

	
	
	/**
	 * delete product
	 * @param ref
	 * @return
	 */
	@DeleteMapping
	public Product delete(@PathVariable String ref) {
		return service.delete(ref);
	}

	
	
}
