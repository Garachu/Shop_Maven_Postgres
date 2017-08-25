package com.shop.module.sale.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by meg on 7/24/17.
 */

@NoArgsConstructor
@Getter
@Setter
public class SaleResponse {

    private String productLabel;

    private String productDescription;

    private int quantity;

    private double total;

    private double profit;


    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private String userName;

}
