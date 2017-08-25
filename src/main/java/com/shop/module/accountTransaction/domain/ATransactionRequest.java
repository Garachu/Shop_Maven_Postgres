package com.shop.module.accountTransaction.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by meg on 8/16/17.
 */

/*
        Data Transfer Object.
        object received by the service layer and serialised to entity.
        A best practice is to structure the Dto as an immutable object
*/

@Getter //used outside package in ATransactionServiceImp createATransaction() method
@AllArgsConstructor
public class ATransactionRequest {

    @NotNull(message = "accountId May Not Be Null")
    String accountId;

    @NotNull(message = "transactionType May Not Be Null")
    String transactionType;

    @NotNull(message = "narration May Not Be Null")
    String narration;

    @NotNull(message = "amount May Not Be Null")
    double amount;

    @NotNull(message = "date May Not Be Null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
