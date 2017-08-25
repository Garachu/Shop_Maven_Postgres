package com.shop.module.account.dao;

import com.shop.module.common.dao.BaseRepository;
import com.shop.module.account.domain.Account;

import java.util.Optional;

/**
 * Created by meg on 8/14/17.
 */

/*
    Dao stands for Data Access Object
    contains the interfaces to get and store objects in the database.
    It returns entity objects but not dto, which are returned by the service layer.
    The Dao depends on the persistence implementation used, for example Hibernate or MongoDB.
 */

public interface  AccountRepository extends BaseRepository<Account, Integer> {
    Optional<Account> findAccountByAccountId(String accountId);
}
