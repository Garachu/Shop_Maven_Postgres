package com.shop.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by meg on 7/18/17.
 */

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImp.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public Product save(Product product) throws DataAccessException {
        Product result = productRepository.save(product);
        return result;
    }

    @Override
    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) throws DataAccessException {
        productRepository.delete(id);
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
