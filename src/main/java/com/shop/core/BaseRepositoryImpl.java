package com.shop.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

/**
 * Created by meg on 7/25/17.
 */

/*
The SimpleJpaRepository is the default implementation of Spring Data JPA repository interfaces.
Because we want to add the methods declared by our base repository interface into all repositories,
we have to create a custom base repository class that extends the SimpleJpaRepository class and implements the BaseRepository interface.
*/

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;
    private static final Logger log = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    /*
    Add a constructor that takes two constructor arguments:
    1. A Class object that represents the managed entity class.
    2. An EntityManager object.
     */
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> jpaEntityInformation, EntityManager entityManager) {
        super(jpaEntityInformation, entityManager);
        this.entityManager = entityManager;
    }


    //@Transactional annotation. This ensures that the method is always invoked inside a read-write transaction.
    @Transactional
    @Override
    public List<T> findAll(int[] range) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(getDomainClass());
        cq.select(c);
        cq.orderBy(cb.desc(c.get("id")));
        Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();




    }

    @Override
    public List<T> findLastSpecifyLimit(int limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> c = cq.from(getDomainClass());
        cq.select(c);
        cq.orderBy(cb.desc(c.get("id")));
        Query q = entityManager.createQuery(cq);
        q.setMaxResults(limit);
        return q.getResultList();
    }


}

