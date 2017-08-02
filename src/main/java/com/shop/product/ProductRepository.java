package com.shop.product;

import com.shop.core.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Created by meg on 7/15/17.
 */

//Get a bunch of CRUD methods
//Allow Spring Data JPA repository infrastructure to scan the classpath for this interface and create a Spring bean for it
interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findByLabel(String label);
    Optional<Product> findById(Integer productId);
}
