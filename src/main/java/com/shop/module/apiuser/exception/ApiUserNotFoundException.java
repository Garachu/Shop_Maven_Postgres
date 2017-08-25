package com.shop.module.apiuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by meg on 8/23/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiUserNotFoundException extends RuntimeException{
    public ApiUserNotFoundException(){super("ApiUser Not Found");}
}
