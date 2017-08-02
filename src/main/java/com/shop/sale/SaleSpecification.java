package com.shop.sale;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by meg on 7/25/17.
 */
public class SaleSpecification implements Specification<Sale>{

    Sale filter;

    public SaleSpecification(Sale filter){
        super();
        this.filter = filter;

    }

    @Override
    public Predicate toPredicate(Root<Sale> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();
        return p;
    }
}
