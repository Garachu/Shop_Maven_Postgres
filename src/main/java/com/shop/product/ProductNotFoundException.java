package com.shop.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by meg on 7/21/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(int productId) {

        super("could not find product with id:  '" + productId + "'.");
    }

    public ProductNotFoundException() {
        super("Product Not Found");
    }
}
