package com.shop.account;


import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by meg on 8/14/17.
 */


@Service
@Transactional
@Slf4j
class AccountServiceImp implements AccountService{

    private final AccountRepository accountRepository;

    AccountServiceImp(AccountRepository accountRepository){

        this.accountRepository = accountRepository;
    }

    @Override
    @Cacheable(value ="accounts", key = "#accountId")
    public Account findAccountByAccountId(String accountId) {
        log.info("Begin findAccountByAccountId");
        return accountRepository.findAccountByAccountId(accountId).orElseThrow(
                () ->new  AccountNotFoundException(accountId)
        );

    }

    @Override
    @CachePut(value = "accounts", key="#result.accountId")
    public Account createAccount(Account account)
    {
        return accountRepository.save(account);
    }

    @Override
    @Cacheable(value = "accounts")
    public List<Account> findLastWithLimit(int limit) {
       return accountRepository.findLastSpecifyLimit(limit);
    }

    @Override
    @CachePut(value = "accounts", key = "#accounts.accountId")
    public Account updateAccount(Account account) {

        return accountRepository.save(account);
    }

    @Override
    @CacheEvict(value = "accounts", key="#accountId")
    public void delete(String accountId) {

    }

    @Override
    @CacheEvict(value = "accounts", allEntries = true)
    public void evictCache() {

    }

}
