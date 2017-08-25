package com.shop.module.product.service;

import com.shop.module.product.dao.ProductRepository;
import com.shop.module.product.domain.Product;
import com.shop.module.product.domain.ProductRequest;
import com.shop.module.product.domain.ProductResponse;
import com.shop.module.product.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by meg on 7/18/17.
 */

@Service
@Transactional
@Slf4j
class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;


    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    //Fetches all the products in asc order
    @Override
    public List<ProductResponse> findAll() {
        List<Product> list = productRepository.findAll();
        log.info("findAll() records found:  " + list.size());
        return list.stream().map(product ->
                ProductResponse.convertoDTO(product)
        ).collect(Collectors.toList());
    }

    //Add a single Product
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = ProductRequest.convertToEntity(productRequest);
        return ProductResponse.convertoDTO(productRepository.save(product));
    }

    //Add an Array of Products
    @Override
    public List<ProductResponse> addProducts(ProductRequest[] productRequests) {
        List<Product> saved = productRepository.save(   Arrays.stream(productRequests).map(productRequest ->
                ProductRequest.convertToEntity(productRequest)).collect(Collectors.toList()));

       return saved.stream().map(product ->
               ProductResponse.convertoDTO(product)).collect(Collectors.toList());

    }

    //find product by productId
    @Override
    public ProductResponse findProduct(Integer id) {
           return ProductResponse.convertoDTO( productRepository.findById(id).orElseThrow(
                () ->  new ProductNotFoundException()
        ));
    }

    //set product recordstate to false on request to delete product
    @Override
    public Boolean deleteProduct(Integer id) {
       Product product = productRepository.findById(id).orElseThrow( () ->
                new ProductNotFoundException());
       product.setRecordstate(Boolean.FALSE);
       return product.getRecordstate();
    }


    @Override
    public List<Product> findByLabel(String label) {
        return productRepository.findByLabel(label);
    }

    @Override
    public Optional<Product> findByProductId(Integer productId) {

        return productRepository.findById(productId);
    }

    @Override
    public void validateProduct(int productId) {
        productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException(productId)
        );
    }



}
