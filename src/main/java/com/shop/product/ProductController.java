package com.shop.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by meg on 7/11/17.
 */


@RestController
public class ProductController {

    private  static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productService;


    @RequestMapping("/products")
    public List<Product> getProducts() {
        return (List<Product>) productService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public Product add(@RequestBody Product product) {
        Product result = productService.save(product);
        return result;
    }

    @RequestMapping("/products/{id}")
    public Product findProduct(@PathVariable Long id){
        return productService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }




}
