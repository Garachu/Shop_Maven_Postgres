package com.shop.module.accountTransaction.domain;

import com.shop.module.common.util.DateUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by meg on 8/17/17.
 */

/*
        Data Transfer Object.
        object returned by the service layer and serialised to Json.
        A best practice is to structure the Dto as an immutable object
*/

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class ATransactionResponse {

    private String transactionType;
    private String narration;
    private double amount;
    private double balance;
    private String date;

    public static ATransactionResponse convertoDTO(ATransaction aTransaction) {
        //ATransactionResponse dto = modelMapper.map(aTransaction, ATransactionResponse.class);
        ATransactionResponse dto = new ATransactionResponse();
        dto.setTransactionType(aTransaction.getTransactionType());
        dto.setNarration(aTransaction.getNarration());
        dto.setAmount(aTransaction.getAmount());
        dto.setBalance(aTransaction.getBalance());
        dto.setDate(DateUtil.formatDate(aTransaction.getDate(), ""));
        return dto;
    }

}
