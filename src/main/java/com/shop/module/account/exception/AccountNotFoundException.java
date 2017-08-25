package com.shop.module.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by meg on 8/14/17.
 */

/*
    It is a good practice to use your own custom exceptions, which can be defined in the exception package.
    exception messages should be encoded with a prefix, for example ERROR_, this way the user interface can translate all the messages starting with ERROR_
    The error messages and other utility classes can be stored in the util package.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String accountId) {
        super("could not find account with id:  '" + accountId + "'.");
    }
}
