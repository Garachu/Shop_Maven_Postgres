package com.shop.core.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by meg on 7/25/17.
 */

//This ensures that Spring Data JPA won’t try to create an implementation for the BaseRepository interface.This ensures that Spring Data JPA won’t try to create an implementation for the BaseRepository interface.
@NoRepositoryBean
//The T type parameter is the type of the managed entity.
//The ID type parameter is the type of the managed entity’s primary key.
//base repository interface must extend the same interface that is extended by your repository interfaces.
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    List<T> findAll(int[] range);
    List<T> findLastSpecifyLimit(int limit);
}
