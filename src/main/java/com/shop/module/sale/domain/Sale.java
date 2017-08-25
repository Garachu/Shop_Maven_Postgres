package com.shop.module.sale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.module.common.domain.BaseEntity;
import com.shop.module.product.domain.Product;
import com.shop.module.user.domain.SUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by meg on 7/15/17.
 */

@Getter
@Setter
@Entity
@Table(name = "sale", catalog = "shop_home_test", schema = "base")
public class Sale extends BaseEntity {

    @NotNull
    private int sp;

    @NotNull
    private int quantity;

    @NotNull
    private Date timestamp;

    @NotNull
    private int profit;

    @ManyToOne()
    @JoinColumn(name = "product")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "suser")
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

}
