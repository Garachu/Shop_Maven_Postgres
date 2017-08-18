package com.shop.accountTransaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.account.Account;
import com.shop.account.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by meg on 8/16/17.
 */

@Service
@Transactional
@Slf4j
public class ATransactionServiceImp implements ATransactionService {

    private final AccountService accountService;
    private final ATransactionRepository aTransactionRepository;

    @Autowired
    ATransactionServiceImp(AccountService accountService, ATransactionRepository aTransactionRepository) {
        this.accountService = accountService;
        this.aTransactionRepository = aTransactionRepository;
    }

    @Override
    public ATransaction createATransaction(ATransactionInModel aTransactionInModel) {

        //Fetch the Account first
        Account account = accountService.findAccountByAccountId(aTransactionInModel.getAccountId());

        //Determine the Account Type Creditor/Debtor
        switch (account.getAccountType()) {

            case "Creditor":
                //Determine the Transaction Type, Is it More credit or payment
                switch (aTransactionInModel.getTransactionType()) {

                    //Paying Our Creditor either full amount or partial amount
                    case "Credit":

                        //Update the Creditor Account Balance
                        double balance = account.getBalance() - aTransactionInModel.getAmount();
                        account.setBalance(balance);

                        //Update Account lastModifiedDate
                        Date date = Date.from(aTransactionInModel.date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                        account.setLastModifiedDate(date);

                        //Create ATransaction
                        ATransaction aTransaction = new ATransaction(
                                account,
                                aTransactionInModel.getTransactionType(),
                                aTransactionInModel.getNarration(),
                                aTransactionInModel.getAmount(),
                                date,
                                balance
                        );

                        //Persist ATransaction to database
                        aTransactionRepository.save(aTransaction);

                        break;
                    case "":
                        break;
                }
                break;
            case "Debtor":
                break;
        }

        return null;
    }

    @Override
    public List<ATransaction> fetchATransactionsByAccountAccountId(String accountId) {
        return aTransactionRepository.findATransactionsByAccount_AccountId(accountId);
    }

}
