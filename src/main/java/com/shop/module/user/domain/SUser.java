package com.shop.module.user.domain;

import com.shop.module.common.domain.BaseEntity;
import com.shop.module.sale.domain.Sale;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@NoArgsConstructor
@Entity
@Table(name = "user", catalog = "shop_home_test", schema = "base")
public class SUser extends BaseEntity{

    @NotNull(message = "fullname must not be blank!")
    private String fullname;

    @NotNull(message = "username must not be blank!")
    private String username;

    @NotNull(message = "password must not be blank!")
    @Getter
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Sale> sales;

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }





}
