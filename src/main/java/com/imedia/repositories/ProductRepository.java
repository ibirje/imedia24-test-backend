package com.imedia.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import com.imedia.dto.ProductDto;
import com.imedia.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecs<Product>  {

	
	@Query("SELECT new com.imedia.dto.ProductDto(name, ref, image, price, description, category.name) "
		 + "FROM Product WHERE enabled = 1 and categoryid = :catid")
	public List<ProductDto> findByCategoryId(@Param("catid") Long catid);
	
	@Query("SELECT new com.imedia.dto.ProductDto(name, ref, image, price, description, category.name) "
		 + "FROM Product WHERE enabled = 1 and categoryid in (:catids)")
	public List<ProductDto> findByCategoryIdList(@Param("catids") HashSet<Long> catids);
	
	public Page<Product> findAll(Pageable p);
     
	public Product findByRef(String ref);

	@Override
	<S extends Product> S save(S entity);

	@Override
	public Optional<Product> findById(Long id);

	@Override
	void delete(Product entity);

	@Query("SELECT new com.imedia.dto.ProductDto(name, ref, image, price, description, category.name) FROM Product")
	public List<ProductDto> findAllDtos();

	Page<ProductDto> findAllByOrderByIdDesc(@Nullable Specification<Product> spec, Pageable pageable);
	Page<ProductDto> findAllByOrderByIdDesc(Pageable pageable);

	@Query("SELECT P FROM Product P Where P.name = :title")
	public Product findByName(String title);

}


