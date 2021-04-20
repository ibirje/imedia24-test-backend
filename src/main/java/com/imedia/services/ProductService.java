package com.imedia.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.imedia.dto.ListResponse;
import com.imedia.dto.ProductDto;
import com.imedia.dto.ProductInsertRequest;
import com.imedia.dto.ProductUpdateRequest;
import com.imedia.dto.ProductsByCategoryResponse;
import com.imedia.models.Category;
import com.imedia.models.Product;
import com.imedia.repositories.ProductRepository;
import com.imedia.utils.Utils;
import com.imedia.utils.Validator;
import com.imedia.utils.constants.Const;

@Service
public class ProductService {

	@Autowired
	private CategoryService categservice;

	@Autowired
	private ProductRepository repo;

	@Autowired
	private Validator v;

	@Autowired
	private Utils utils;

	@Autowired
	private EntityManager em;

	@Autowired
	private CurrencyService currencyservice;
	
	
	
	public ProductService() {
	}
	
	
	public Product update(ProductUpdateRequest p) {
		try {
			String ref = p.getRef();
			Product newp = repo.findByRef(ref);

			if (p.getProduct().equals(newp))
				return newp;

			
			if(p.getCurrency() != null && !p.getCurrency().toLowerCase().contentEquals("eur")) {
				
				Double price = currencyservice.convertCurrencyToEur(p.getProduct().getPrice(), p.getCurrency() ).getBaseAmount();
				p.getProduct().setPrice(price);
			}
			
			newp.copy(p.getProduct());

			if (newp.getCategoryName().equals(p.getProduct().getCategoryName())) {
				Category categ = categservice.findByName(newp.getCategoryName());
				if (categ != null)
					newp.setCategoryid(categ.getId());
			}

			repo.save(newp);
			return newp;

		} catch (Exception ex) {
			utils.error(ex);
			return null;
		}
	}

	
	
	public Product delete(String ref) {
		try {
			Product p = repo.findByRef(ref);
			repo.delete(p);
			return p;
		} catch (Exception ex) {
			utils.error(ex);
			return null;
		}
	}

	
	
	
	public ListResponse<ProductDto> listSearch(String titre, String orderby, Integer size, Integer page) {

		if(size == null || size < 0) size = Const.DEFAULT_SIZE;
		if(page == null || page < 0) page = 0;

		Specification<Product> spec = v.isNullOrEmpty(titre) ? null : repo.match("name");
		
		if (spec != null || !v.isNullOrEmpty(orderby)) {

			if (v.isNullOrEmpty(orderby))
				orderby = "id desc";

			return  recherche(spec, titre, orderby, page, size);
		}

		return new ListResponse<ProductDto>(repo.findAllByOrderByIdDesc(PageRequest.of(page, size)));
		
	}

	
	
	/**
	 * full text search using criteria querries
	 * 
	 * @param spec
	 * @param param
	 * @param orderby
	 * @param page
	 * @param size
	 * @return
	 */
	public ListResponse<ProductDto> recherche(Specification<Product> spec, String param, String orderby, Integer page,
			Integer size) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		/* -----------------------------------------------------------------------------
			setting up criteriaquery for result products
		* -----------------------------------------------------------------------------*/
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		criteria.where(spec.toPredicate(root, criteria, builder));
		

		/* -----------------------------------------------------------------------------
			setting up criteriaquery for pagination data (total elements & number of pages )
		* -----------------------------------------------------------------------------*/
		CriteriaQuery<Long> countcriteria = builder.createQuery(Long.class);
		countcriteria.select(builder.count( countcriteria.from(Product.class)));
		countcriteria.where(spec.toPredicate(root, countcriteria, builder));
		
		
		/* -----------------------------------------------------------------------------
			adding order by to the queries
		* -----------------------------------------------------------------------------*/
		
		String[] suborder = orderby.split(" ");
		if (suborder != null && suborder.length > 0) {

			Order order = suborder.length > 1 && suborder[1].toLowerCase().equals("desc") ?
				builder.desc(root.get(suborder[0])) 
				: builder.asc(root.get(suborder[0]));
			
			criteria.orderBy(order);
			countcriteria.orderBy(order);
		}

		
		/* -----------------------------------------------------------------------------
			adding the text param to the queries
		* -----------------------------------------------------------------------------*/
		TypedQuery<Product> query = em.createQuery(criteria);
		TypedQuery<Long> countquery = em.createQuery(countcriteria);

		if (!v.isNullOrEmpty(param)) {
			
			String tx = "+" + param.replaceAll("[^-\\wéèàêô\\s]", "").trim().replace(" ", "* +") + "*";
			
			query.setParameter("text", tx.replace("+-", "-"));
			countquery.setParameter("text", tx.replace("+-", "-"));
		}

		
		/* -----------------------------------------------------------------------------
			wrapping the results ( product list, pagescount, itemcount )
		* -----------------------------------------------------------------------------*/
		Long count = countquery.getSingleResult();
		if(count == 0)
		return new ListResponse<ProductDto>(null, 0l, 0l);
		
		query.setFirstResult(page * size).setMaxResults(size);
		
		List<ProductDto> products = query.getResultList().stream()
				.map(t->new ProductDto(t))
				.collect(Collectors.toList());

		return new ListResponse<ProductDto>(products, count/size+1, count);
	}

	
	
	
	public List<ProductDto> findByCategoryIdList(HashSet<Long> ids) {
		return repo.findByCategoryIdList(ids);
	}
	

	public Collection<ProductDto> findByCategoryId(long id) {
		return repo.findByCategoryId(id);
	}
	

	public ProductsByCategoryResponse findByCategoryName(String categ) {
		if(v.isNullOrEmpty(categ))
			return new ProductsByCategoryResponse(null, repo.findAllDtos());
		Category cat = categservice.findByName(categ);
		if(cat == null) return null;
		HashSet<Long> catchildren = categservice.findChildrenById(cat.getId());
		catchildren.add(cat.getId());
		List<ProductDto> prods = findByCategoryIdList(catchildren);
		
		return new ProductsByCategoryResponse(cat, prods);
	}

	
	
	
	public Product add(ProductInsertRequest p) throws ServletException {
		
		if(p.getCurrency() != null && !p.getCurrency().toLowerCase().contentEquals("eur")) {
			
			Double price = currencyservice.convertCurrencyToEur(p.getProduct().getPrice(), p.getCurrency() ).getBaseAmount();
			p.getProduct().setPrice(price);
		}
		
		return repo.save(p.getProduct());
	}




	public Product findByName(String title) {
		Product p = repo.findByName(title);
		return p;
	}


}
