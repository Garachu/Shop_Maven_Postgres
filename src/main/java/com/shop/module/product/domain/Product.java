package com.shop.module.product.domain;

import com.shop.module.common.domain.BaseEntity;
import com.shop.module.sale.domain.Sale;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meg on 7/15/17.
 */

@NoArgsConstructor
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(name = "product", catalog = "shop_home_test", schema = "base")
public class Product extends BaseEntity{

    @NotNull(message = "label may not be null")
    @Size(min = 2, max = 40)
    private String label;

    //@NotNull(message = "description may not be null")
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
    private List<Sale> sales = new ArrayList<>();

    public void setRecordstate(Boolean recordstate) {
        this.recordstate = recordstate;
    }

    public Boolean getRecordstate() {
        return recordstate;
    }
}
