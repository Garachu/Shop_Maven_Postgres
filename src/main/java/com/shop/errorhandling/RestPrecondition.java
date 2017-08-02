package com.shop.errorhandling;

import com.shop.product.ProductNotFoundException;
import com.shop.sale.SaleNotFoundException;

/**
 * Created by meg on 7/23/17.
 */

public class RestPrecondition {

    public static <T> T checkSaleFound(T resource) {
        if (resource == null) {
            throw new SaleNotFoundException();
        }
        return resource;
    }

    public static <T> T checkUserFound(T resource) {
        if (resource == null) {
            throw new SaleNotFoundException();
        }
        return resource;
    }

    public static <T> T checkProductFound(T resource) {
        if (resource == null) {
            throw new ProductNotFoundException();
        }
        return resource;
    }

}
