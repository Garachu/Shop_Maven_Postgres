package com.shop.module.product.rest;

import com.shop.module.common.domain.Result;
import com.shop.module.common.domain.ValidationError;
import com.shop.module.common.util.RestPrecondition;
import com.shop.module.common.util.ValidationErrorBuilder;
import com.shop.module.common.util.ValidatorUtil;
import com.shop.module.product.domain.ProductRequest;
import com.shop.module.product.domain.ProductResponse;
import com.shop.module.product.service.ProductService;
import com.shop.module.product.SearchCriteria;
import com.shop.module.product.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by meg on 7/11/17.
 */

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public ProductController(ProductService productService, ValidatorUtil validatorUtil) {

        this.productService = productService;
        this.validatorUtil = validatorUtil;
    }


    //find all products
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll(){
        Result result = new Result();
        List<ProductResponse> list = productService.findAll();
        String message = list.isEmpty() ? "0 Records Found" : "success";
        result.setMessage(message);
        result.setResult(list);
        return ResponseEntity.ok(result);
    }

    //add single product
    @RequestMapping(method = RequestMethod.POST, value = "/add/one")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequest productRequest, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Result result = new Result();
        result.setMessage("success");
        result.setResult(Arrays.asList(productService.addProduct(productRequest)));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //add products in bulk
    @RequestMapping(method =  RequestMethod.POST, value = "add/bulk")
    public ResponseEntity<?> addProducts(@Valid @RequestBody ProductRequest[] productRequests, Errors errors){
        if(errors.hasErrors()){
            log.error("Error validating ProductRequest[] in addProducts()");
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        for(ProductRequest productRequest: productRequests){
            ResponseEntity<?> rsps = validatorUtil.validate(productRequest);
            if(rsps != null){
                log.info("rsps != null");
                return rsps;
            }
        }

        Result result = new Result();
        List<ProductResponse> list = productService.addProducts(productRequests);
        result.setMessage("success");
        result.setResult(list);
        return ResponseEntity.ok(result);
    }



    //find product by productId
    //GET         shop/products/id                    Get A Product
    @RequestMapping(method = RequestMethod.GET, value = "/{productId}")
    public ProductResponse findProduct(@PathVariable Integer productId) {
        return RestPrecondition.checkProductFound(productService.findProduct(productId));
    }

    //delete product by productId
    //GET         shop/products/productId                    Delete A Product
    @RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
    public  ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        if(productService.deleteProduct(productId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //POST        shop/products/search                Search For Products By Product Label
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public ResponseEntity<?> searchByProductLabel(@Valid @RequestBody SearchCriteria search, Errors errors) {
        Result result = new Result();
        List<Product> products = productService.findByLabel(search.getLabel());
        if (products.isEmpty()) {
            result.setMessage("No Product Found!");
        } else {
            result.setMessage("success");
        }

        result.setResult(products);

        return ResponseEntity.ok(result);
    }



}
