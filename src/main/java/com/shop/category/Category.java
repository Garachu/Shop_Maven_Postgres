package com.shop.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.core.BaseEntity;
import com.shop.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@Entity
public class Category{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String label;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //@JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Category() {
    }

    public Category(String label, String description) {
        this();
        this.label = label;
        this.description = description;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
