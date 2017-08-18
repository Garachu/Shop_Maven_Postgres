package com.shop.user;

import com.shop.core.entity.BaseEntity;
import com.shop.sale.Sale;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@Entity
@Table(name = "user", catalog = "shop_home_test", schema = "base")
public class SUser extends BaseEntity{

    @NotBlank(message = "fullname must not be blank!")
    private String fullname;
    @NotBlank(message = "username must not be blank!")
    private String username;
    @NotBlank(message = "password must not be blank!")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Sale> sales;

    public SUser() {
        super();
    }

    public SUser(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
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
