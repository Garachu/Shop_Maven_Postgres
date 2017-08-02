package com.shop.product;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by meg on 7/25/17.
 */

public class ProductSpecification implements Specification<Product>{

    private Product filter;

    public ProductSpecification(Product filter){
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        Predicate p = cb.disjunction();

        if(filter.getLabel() != null){
            p.getExpressions()
                    .add(cb.equal(root.get("name"), filter.getLabel()));
        }
        return p;
    }
}
