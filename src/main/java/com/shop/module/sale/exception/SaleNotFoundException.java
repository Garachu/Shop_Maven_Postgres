package com.shop.module.sale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by meg on 7/23/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SaleNotFoundException extends RuntimeException{
    public SaleNotFoundException(int saleId) {
        super("could not find product with id:  '" + saleId + "'.");
    }

    public SaleNotFoundException() {
        super("could not find sale item");
    }
}
