package com.imedia.repositories;

import javax.persistence.criteria.Expression;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaSpecs<T> extends JpaSpecificationExecutor<T> {

	
	
	public default Specification<T> match(String param) {
		return (t, cq, cb) -> {
			Expression<Double> match = cb.function("match", Double.class, t.get(param), cb.parameter(String.class, "text"));
			return cb.greaterThan(match, 0d);
		};
	}
}