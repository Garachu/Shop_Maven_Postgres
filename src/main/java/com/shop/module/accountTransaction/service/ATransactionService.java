package com.shop.module.accountTransaction.service;

import com.shop.module.accountTransaction.domain.ATransaction;
import com.shop.module.accountTransaction.domain.ATransactionRequest;
import com.shop.module.accountTransaction.domain.ATransactionResponse;

import java.util.List;

/**
 * Created by meg on 8/16/17.
 */


public interface ATransactionService {
    ATransactionResponse createATransaction(ATransactionRequest aTransactionInModel);
    List<ATransaction> fetchATransactionsByAccountAccountId(String accountId);
    List<ATransactionResponse> fetchATransactionsByAccountOrderByDateDesc(String accountId);

}
