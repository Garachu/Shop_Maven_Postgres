package com.shop.sale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.product.Product;
import com.shop.user.SUser;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by meg on 7/23/17.
 */
public class SaleInModel {

    @NotNull
    private int sp;

    @NotNull
    private int quantity;

    @NotNull
    private String date;

    @NotNull
    private int productId;

    @NotNull
    private int userId;

    public SaleInModel() {
    }

    public SaleInModel(int sp, int quantity, String date, int productId, int userId) {
        this.sp = sp;
        this.quantity = quantity;
        this.date = date;
        this.productId = productId;
        this.userId = userId;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
