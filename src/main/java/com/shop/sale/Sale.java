package com.shop.sale;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.core.entity.BaseEntity;
import com.shop.product.Product;
import com.shop.user.SUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by meg on 7/15/17.
 */

@Entity
@Table(name = "sale", catalog = "shop_home_test", schema = "base")
public class Sale extends BaseEntity{

    @NotNull
    private int sp;

    @NotNull
    private int quantity;

    @NotNull
    private Date timestamp;

    @NotNull
    private int profit;

    @ManyToOne()
    @JoinColumn(name="product")
    //@JsonIgnore
    private Product product;

    @ManyToOne()
    @JoinColumn(name="suser")
    @JsonIgnore
    private SUser user;

    public Sale() {
        super();
    }

    public Sale(int sp, int quantity, Date timestamp, Product product, int profit, SUser user) {
        this();
        this.sp = sp;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.product = product;
        this.profit = profit;
        this.user = user;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public SUser getUser() {
        return user;
    }

    public void setUser(SUser user) {
        this.user = user;
    }
}
