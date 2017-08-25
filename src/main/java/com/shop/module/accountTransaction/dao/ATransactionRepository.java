package com.shop.module.accountTransaction.dao;

import com.shop.module.accountTransaction.domain.ATransaction;
import com.shop.module.common.dao.BaseRepository;

import java.util.List;

/**
 * Created by meg on 8/16/17.
 */

public interface ATransactionRepository extends BaseRepository<ATransaction,Integer> {
    List<ATransaction> findATransactionsByAccount_AccountId(String accountId);
    List<ATransaction> findATransactionsByAccount_AccountIdOrderByDateDesc(String accountId);
}
