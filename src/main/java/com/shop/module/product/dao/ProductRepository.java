package com.shop.module.product.dao;

import com.shop.module.common.dao.BaseRepository;
import com.shop.module.product.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Created by meg on 7/15/17.
 */

public interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findByLabel(String label);
    Optional<Product> findById(Integer productId);
    Optional<List<Product>> findByLabelContainingIgnoreCase(String label);
}
