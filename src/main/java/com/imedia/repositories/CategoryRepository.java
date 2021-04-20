package com.imedia.repositories;

import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.imedia.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

	Category findByName(@Param("name") String name);
	
	@Query(" SELECT child.id "
		 + " FROM Category parent "
		 + " JOIN Category child "
		 + " ON parent.id = child.parentCategoryId "
		 + " WHERE parent.id = :id")
	HashSet<Long> findChildrenById(Long id);
	
}
