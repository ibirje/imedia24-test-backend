package com.imedia.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imedia.models.Category;
import com.imedia.models.Product;
import com.imedia.repositories.CategoryRepository;

@Service
public class CategoryService {
/*
 * generate test
 * test parent child tree function
 * 
 */

	@Autowired
	ProductService productservice;
	
	@Autowired
	CategoryRepository repo;

	
	
	public Iterable<Category> getAll() {
		return repo.findAll();
	}

	
	
	public Category findByName(String name) {
		return repo.findByName(name);
	}



	public HashSet<Long> findChildrenById(Long id) {
		
		return findAllChildrenIds(id);
	}
	
	/**
	 * adding cache here, will cache all parent nodes in a tree 
	 * which will reduce the load time significantly after the few first calls
	 * @param id
	 * @return
	 */
	private HashSet<Long> findAllChildrenIds(Long id) {
		
		HashSet<Long> newids = repo.findChildrenById(id);
		for(Long childid : newids)
			newids.addAll(findAllChildrenIds(childid));
		return newids;
	}



	public Product add(Category categ) {
		// TODO Auto-generated method stub
		return null;
	}



	public Product update(Category categ) {
		// TODO Auto-generated method stub
		return null;
	}



	public Product delete(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
