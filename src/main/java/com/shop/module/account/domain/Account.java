package com.shop.module.account.domain;

import com.shop.module.accountTransaction.domain.ATransaction;
import com.shop.module.common.domain.BaseEntity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by meg on 8/14/17.
 */

//object to database mapping, which can use for example Hibernate or Spring Data annotations.

@Entity
@Table(name = "account", catalog = "shop_home_test", schema = "base")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class Account extends BaseEntity implements Serializable{

    @NotNull
    @Column(unique=true)
    private String accountId;

    @NotNull
    private String accountName;

    @NotNull
    private String accountContact;

    @NotNull
    @Getter(AccessLevel.PUBLIC)
    private String accountType;

    @NotNull
    private String accountNarration;

    @NotNull
    private Date firstModifiedDate;

    @NotNull
    @Setter(AccessLevel.PUBLIC)
    @Getter(AccessLevel.PUBLIC)
    private Date lastModifiedDate;

    @NotNull
    private double initialBalance;

    @NotNull
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private double balance;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<ATransaction> aTransactions = new ArrayList<>();

}
