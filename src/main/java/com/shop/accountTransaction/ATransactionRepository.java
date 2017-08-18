package com.shop.accountTransaction;

import com.shop.core.repository.BaseRepository;

import java.util.List;

/**
 * Created by meg on 8/16/17.
 */

interface ATransactionRepository extends BaseRepository<ATransaction,Integer> {
    List<ATransaction> findATransactionsByAccount_AccountId(String accountId);
}
