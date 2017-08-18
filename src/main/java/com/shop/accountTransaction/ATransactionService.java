package com.shop.accountTransaction;

import java.util.List;

/**
 * Created by meg on 8/16/17.
 */


public interface ATransactionService {
    ATransaction createATransaction(ATransactionInModel aTransactionInModel);
    List<ATransaction> fetchATransactionsByAccountAccountId(String accountId);

}
