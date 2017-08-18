package com.shop.product;

import com.shop.core.Result;
import com.shop.core.errorhandling.RestPrecondition;
import com.shop.core.errorhandling.ValidationErrorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //GET         shop/products                       Get all Products
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.findAll();
        Result result = new Result();
        if (products.isEmpty()) {
            result.setMessage("No Products Found!");
        } else {
            result.setMessage("success");
        }
        result.setResult(products);
        return ResponseEntity.ok(result);
    }

    //POST        shop/products                       Create new Product
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@Valid @RequestBody Product product, Errors errors) {
        if(errors.hasErrors()){
            return  ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Result result = new Result();
        try {
            Product saved = productService.save(product);
            if (saved == null) {
                result.setMessage("Product was not Created!");
            } else {
                result.setMessage("success");
            }
            result.setResult(Arrays.asList(saved));
            return ResponseEntity.status(HttpStatus.CREATED.value()).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(result);
        }
    }

    //GET         shop/products/id                    Get A Product
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Product findProduct(@PathVariable Integer id) {
        return RestPrecondition.checkProductFound(productService.findOne(id));
    }

    //DELETE      shop/products/id                    Delete the Product
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        RestPrecondition.checkProductFound(productService.findOne(id));
        Result result = new Result();
        try {
            productService.delete(id);
            result.setMessage("Success");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(result);
        }
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
