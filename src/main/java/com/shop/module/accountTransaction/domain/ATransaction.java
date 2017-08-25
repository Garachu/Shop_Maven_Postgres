package com.shop.module.accountTransaction.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.module.account.domain.Account;
import com.shop.module.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by meg on 8/16/17.
 */

@Entity
@Table(name = "account_transaction", catalog = "shop_home_test", schema = "base")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
public class ATransaction extends BaseEntity{

    @ManyToOne()
    @JoinColumn(name="account")
    private Account account;

    @NotNull
    private String transactionType;

    @NotNull
    private String narration;

    @NotNull
    private double amount;

    @NotNull
    private Date date;

    @NotNull
    private double balance;


    public ATransaction(Account account,
                        String transactionType,
                        String narration,
                        double amount,
                        Date date,
                        double balance) {

        this.account = account;
        this.transactionType = transactionType;
        this.narration = narration;
        this.amount = amount;
        this.date = date;
        this.balance = balance;

    }
}
