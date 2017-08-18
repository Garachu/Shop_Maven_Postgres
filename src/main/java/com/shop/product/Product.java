package com.shop.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.core.entity.BaseEntity;
import com.shop.sale.Sale;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@Entity
@Table(name = "product", catalog = "shop_home_test", schema = "base")
public class Product extends BaseEntity{

    @NotNull(message = "label may not be null")
    @Size(min = 2, max = 40)
    private String label;

    @NotNull(message = "description may not be null")
    private String description;

    @NotNull(message = "bp may not be null")
    private int bp;

    @NotNull(message = "price may not be null")
    private int price;

    private Boolean recordstate;

    @NotNull
    @Size(min = 2, max = 40)
    private String category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    @JsonIgnore
    private List<Sale> sales = new ArrayList<>();

    public Product() {
        super();
    }

    public Product(String label, String description, int bp, int price, Boolean recordstate, String category) {
        this();
        this.label = label;
        this.description = description;
        this.bp = bp;
        this.price = price;
        this.recordstate = recordstate;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
