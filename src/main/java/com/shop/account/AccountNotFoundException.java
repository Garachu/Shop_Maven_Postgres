package com.shop.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by meg on 8/14/17.
 */



@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String accountId) {
        super("could not find account with id:  '" + accountId + "'.");
    }
}
