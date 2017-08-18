package com.shop.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.accountTransaction.ATransaction;
import com.shop.core.entity.BaseEntity;
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



@Entity
@Table(name = "account", catalog = "shop_home_test", schema = "base")
@Getter
@Setter
@RequiredArgsConstructor
public class Account extends BaseEntity implements Serializable{

    @NotNull
    @Column(unique=true)
    private String accountId;

    @NotNull
    private String accountName;

    @NotNull
    private String accountContact;

    @NotNull
    private String accountType;

    @NotNull
    private String accountNarration;

    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date firstModifiedDate;

    @NotNull
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastModifiedDate;

    @NotNull
    private double initialBalance;

    @NotNull
    private double balance;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    @JsonIgnore
    @Getter(onMethod = @__( @JsonIgnore ))
    @Setter
    private List<ATransaction> aTransactions = new ArrayList<>();


    public Account(String accountId,
                   String accountName,
                   String accountContact,
                   String accountType,
                   String accountNarration,
                   Date firstModifiedDate,
                   Date lastModifiedDate,
                   double initialBalance,
                   double balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountContact = accountContact;
        this.accountType = accountType;
        this.accountNarration = accountNarration;
        this.firstModifiedDate = firstModifiedDate;
        this.lastModifiedDate = lastModifiedDate;
        this.initialBalance = initialBalance;
        this.balance = balance;
    }
}
