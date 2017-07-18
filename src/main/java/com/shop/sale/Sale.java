package com.shop.sale;

import com.shop.core.BaseEntity;
import com.shop.product.Product;
import com.shop.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by meg on 7/15/17.
 */

@Entity
public class Sale{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int sp;
    private int quantity;
    private Date timestamp;
    private int profit;

    @ManyToOne()
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne()
    @JoinColumn(name="suser")
    private User user;

    public Sale() {
    }

    public Sale(int sp, int quantity, Date timestamp, Product product, int profit, User user) {
        this();
        this.sp = sp;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.product = product;
        this.profit = profit;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
