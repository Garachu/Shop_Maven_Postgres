package com.shop.sale;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by meg on 7/24/17.
 */


public class SaleOutModel {

    private String productLabel;
    private String productDescription;
    private int quantity;
    private double total;
    private double profit;


    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;
    private String userName;

    public SaleOutModel(String productLabel, String productdescription, int quantity, double total, double profit, Date date, String userName) {
        this.productLabel = productLabel;
        this.productDescription = productdescription;
        this.quantity = quantity;
        this.total = total;
        this.profit = profit;
        this.date = date;
        this.userName = userName;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public double getProfit() {
        return profit;
    }

    public Date getDate() {
        return date;
    }

    public String getUserName() {
        return userName;
    }
}
