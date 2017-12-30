package com.shop.module.product.service;

import com.shop.module.product.domain.Product;
import com.shop.module.product.domain.ProductRequest;
import com.shop.module.product.domain.ProductResponse;

import java.util.List;
import java.util.Optional;

/**
 * Created by meg on 7/18/17.
 */

public interface ProductService {

    List<Product> findByLabel(String label);

    Optional<Product> findByProductId(Integer productId);

    void validateProduct(int productId);

    List<ProductResponse> findAll();

    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> addProducts(ProductRequest[] productRequests);

    ProductResponse findProduct(Integer id);

    Boolean deleteProduct(Integer id);

    List<ProductResponse> findByLabelContainingIgnoreCase(String label);

}
