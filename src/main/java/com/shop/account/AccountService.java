package com.shop.account;

import java.util.List;

/**
 * Created by meg on 8/14/17.
 */


public interface AccountService {
    Account findAccountByAccountId(String accountId);
    Account createAccount(Account account);
    List<Account> findLastWithLimit(int limit);
    Account updateAccount(Account account);
    void delete(String accountId);
    void evictCache();
}
