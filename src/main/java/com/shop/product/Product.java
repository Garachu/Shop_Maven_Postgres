package com.shop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.category.Category;
import com.shop.core.BaseEntity;
import com.shop.sale.Sale;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String label;

    private String description;

    private int bp;

    private int price;

    private Boolean recordstate;

    @ManyToOne()
    @JoinColumn(name="category")
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Sale> sales = new ArrayList<>();

    public Product() {
    }

    public Product(String label, String description, int bp, int price, Boolean recordstate, Category category) {
        this();
        this.label = label;
        this.description = description;
        this.bp = bp;
        this.price = price;
        this.recordstate = recordstate;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getRecordstate() {
        return recordstate;
    }

    public void setRecordstate(Boolean recordstate) {
        this.recordstate = recordstate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
