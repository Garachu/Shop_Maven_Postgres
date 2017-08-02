package com.shop.product;

import java.util.List;
import java.util.Optional;

/**
 * Created by meg on 7/18/17.
 */

public interface ProductService {

    List<Product> findAll();

    Product save(Product product);

    Product findOne(Integer id);

    void delete(Integer id);

    List<Product> findByLabel(String label);

    Optional<Product> findByProductId(Integer productId);

    void validateProduct(int productId);

}
