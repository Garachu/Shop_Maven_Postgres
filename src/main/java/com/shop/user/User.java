package com.shop.user;

import com.shop.core.BaseEntity;
import com.shop.sale.Sale;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String fullname;
    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Sale> sales;

    public User() {
    }

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
