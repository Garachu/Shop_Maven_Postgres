package com.shop.module.accountTransaction.service;

import com.shop.module.account.dao.AccountRepository;
import com.shop.module.account.domain.Account;
import com.shop.module.account.domain.AccountResponse;
import com.shop.module.account.exception.AccountNotFoundException;
import com.shop.module.account.service.AccountService;
import com.shop.module.accountTransaction.dao.ATransactionRepository;
import com.shop.module.accountTransaction.domain.ATransaction;
import com.shop.module.accountTransaction.domain.ATransactionRequest;
import com.shop.module.accountTransaction.domain.ATransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 8/16/17.
 */

@Service
@Transactional
@Slf4j
public class ATransactionServiceImp implements ATransactionService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ATransactionRepository aTransactionRepository;
    private final ModelMapper modelMapper;

    @Autowired
    ATransactionServiceImp(AccountService accountService, ATransactionRepository aTransactionRepository, AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.aTransactionRepository = aTransactionRepository;
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    //Create ATransaction Record
    @Override
    public ATransactionResponse createATransaction(ATransactionRequest aTransactionRequest) {

        //Fetch the Account first
        //Account account = accountService.findAccountByAccountId(aTransactionRequest.getAccountId());
        Account account = accountExist(aTransactionRequest.getAccountId());

        ATransactionResponse aTransactionResponse = null;

        //Will store the updated balance
        double balance = 0;

        Date date;

        //Determine the Account Type Creditor/Debtor
        switch (account.getAccountType()) {

            case "Creditor":
                //Determine the Transaction Type, Is it More credit or payment
                switch (aTransactionRequest.getTransactionType()) {

                    //Paying Our Creditor either full amount or partial amount
                    case "Pay":
                        //Update the Creditor Account Balance
                        balance = account.getBalance() - aTransactionRequest.getAmount();
                        account.setBalance(balance);
                        break;

                    case "Borrow":
                        //Update the Creditor Account Balance
                        balance = account.getBalance() + aTransactionRequest.getAmount();
                        account.setBalance(balance);
                        break;
                }

                //Update Account lastModifiedDate
                date = Date.from(aTransactionRequest.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
                account.setLastModifiedDate(date);

                //Create ATransaction
                ATransaction aTransaction = new ATransaction(
                        account,
                        aTransactionRequest.getTransactionType(),
                        aTransactionRequest.getNarration(),
                        aTransactionRequest.getAmount(),
                        date,
                        balance
                );

                //Persist ATransaction to database and return ATransactionResponse
                aTransactionResponse = ATransactionResponse.convertoDTO(aTransactionRepository.save(aTransaction));
                break;
            case "Debtor":
                break;
        }

        return aTransactionResponse;
    }

    //Fetch ATransactions by accountId
    @Override
    public List<ATransaction> fetchATransactionsByAccountAccountId(String accountId) {

        //Fetch the Account first
        //Account account = accountService.findAccountByAccountId(aTransactionRequest.getAccountId());
        Account account = accountExist(accountId);

        return aTransactionRepository.findATransactionsByAccount_AccountId(accountId);
    }

    @Override
    public List<ATransactionResponse> fetchATransactionsByAccountOrderByDateDesc(String accountId) {
        //Fetch the Account first
        //Account account = accountService.findAccountByAccountId(aTransactionRequest.getAccountId());
        Account account = accountExist(accountId);

        //Fetch the ATransactions order by date desc
        List<ATransaction> list = aTransactionRepository.findATransactionsByAccount_AccountIdOrderByDateDesc(accountId);

        //Convert the entity records to dto and return
        return list.stream().map(transaction ->
                ATransactionResponse.convertoDTO(transaction)
        ).collect(Collectors.toList());

    }

    Account accountExist(String accountId){
        return accountRepository.findAccountByAccountId(accountId).orElseThrow(
                () -> new AccountNotFoundException(accountId)
        );
    }


}
