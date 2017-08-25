package com.shop.module.sale.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by meg on 7/23/17.
 */

@Setter
@Getter
@NoArgsConstructor
public class SaleRequest {


    @Digits(integer = 5, fraction = 0, message = "The value of age can not be more than 2 digits")
    @Min(value = 0, message = "The minimum age should be 18")
    @Max(value = 90000, message = "The maximum age can not more than be 50")
    private int sp;

    @NotNull
    private int quantity;

    @NotNull
    private String date;

    @NotNull
    private int productId;

    @NotNull
    private int userId;

    public SaleRequest(int sp, int quantity, String date, int productId, int userId) {
        this.sp = sp;
        this.quantity = quantity;
        this.date = date;
        this.productId = productId;
        this.userId = userId;
    }

}
