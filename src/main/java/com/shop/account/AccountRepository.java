package com.shop.account;

import com.shop.core.repository.BaseRepository;

import java.util.Optional;

/**
 * Created by meg on 8/14/17.
 */

interface  AccountRepository extends BaseRepository<Account, Integer> {
    Optional<Account> findAccountByAccountId(String accountId);
}
