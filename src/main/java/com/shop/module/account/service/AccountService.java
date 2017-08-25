package com.shop.module.account.service;

import com.shop.module.account.domain.Account;
import com.shop.module.account.domain.AccountRequest;
import com.shop.module.account.domain.AccountResponse;

import java.text.ParseException;
import java.util.List;

/**
 * Created by meg on 8/14/17.
 */


public interface AccountService {

    AccountResponse findAccountByAccountId(String accountId);

    AccountResponse addAccount(AccountRequest accountRequest) throws ParseException;

    List<AccountResponse> addAccounts(AccountRequest[] accountRequests);

    List<AccountResponse> findLastWithLimit(int limit);

    Account updateAccount(Account account);
    void delete(String accountId);
    void evictCache();
}
