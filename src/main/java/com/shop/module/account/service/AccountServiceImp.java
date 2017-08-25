package com.shop.module.account.service;


import com.shop.module.account.dao.AccountRepository;
import com.shop.module.account.domain.Account;
import com.shop.module.account.domain.AccountRequest;
import com.shop.module.account.domain.AccountResponse;
import com.shop.module.account.exception.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 8/14/17.
 */

/*
    Service classes implement all the the business logic
    They connect to the Dao layer via dao interfaces.
    Also they return Dto object which will be serialised from the rest layer as Json object.
 */


@Service
@Transactional
@Slf4j
class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImp(AccountRepository accountRepository, ModelMapper modelMapper) {

        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Cacheable(value = "accounts", key = "#accountId")
    public AccountResponse findAccountByAccountId(String accountId) {
        log.info("Begin findAccountByAccountId");
        Account account = accountRepository.findAccountByAccountId(accountId).orElseThrow(
                () -> new AccountNotFoundException(accountId)
        );
        return AccountResponse.convertoDTO(account);
    }

    @Override
    @CachePut(value = "accounts", key = "#result.accountId")
    public AccountResponse addAccount(AccountRequest accountRequest) throws ParseException {

        return AccountResponse.convertoDTO(accountRepository.save(AccountRequest.convertToEntity(accountRequest)));

    }


    @Override
    @Cacheable(value = "accounts")
    public List<AccountResponse> findLastWithLimit(int limit) {
        List<Account> accounts = accountRepository.findLastSpecifyLimit(limit);
        log.info(accounts.size() + " accounts found");
        log.info("convert to dto and return the list");
        return accounts.stream().map(account ->
                AccountResponse.convertoDTO(account)).collect(Collectors.toList());
    }

    @Override
    public List<AccountResponse> addAccounts(AccountRequest[] accountRequests){

        List<Account> accounts= Arrays.stream(accountRequests).map(accountRequest -> AccountRequest.convertToEntity(accountRequest)).collect(Collectors.toList());
        final List<Account> saved = accountRepository.save(accounts);
        return saved.stream().map(account -> AccountResponse.convertoDTO(account)).collect(Collectors.toList());

    }


    @Override
    @CachePut(value = "accounts", key = "#accounts.accountId")
    public Account updateAccount(Account account) {

        return accountRepository.save(account);
    }

    @Override
    @CacheEvict(value = "accounts", key = "#accountId")
    public void delete(String accountId) {

    }

    @Override
    @CacheEvict(value = "accounts", allEntries = true)
    public void evictCache() {

    }


}
