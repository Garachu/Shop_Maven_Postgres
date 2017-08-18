package com.shop.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.core.ApplicationUtil;
import com.shop.core.Result;
import com.shop.core.errorhandling.ValidationErrorBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by meg on 8/14/17.
 */

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountService accountService;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public AccountRestController(AccountService accountService, ValidatorUtil validatorUtil) {
        this.accountService = accountService;
        this.validatorUtil = validatorUtil;
    }

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<Account> findByAccountId(@PathVariable String accountId) {
        Account account = accountService.findAccountByAccountId(accountId);
        return ResponseEntity.ok(account);
    }

    ////Create One Account Record
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountInModel am, Errors errors) throws ParseException {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Result result = new Result();
        Date date = ApplicationUtil.parseDate(am.getDate());
        Account saved = accountService.createAccount(new Account(
                am.accountId,
                am.accountName,
                am.accountContact,
                am.accountType,
                am.accountNarration,
                date,
                date,
                am.balance,
                am.balance
        ));
        result.setMessage("success");
        result.setResult(Arrays.asList(saved));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(result);
    }

    //Fetch Last 20 Account Records
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findLastWithLimit() {
        List<Account> list = accountService.findLastWithLimit(20);
        Result result = new Result();
        if (list.isEmpty()) {
            result.setMessage("0 Records Found.");
        } else {
            result.setMessage("success");
        }
        result.setResult(list);
        return ResponseEntity.ok(result);
    }


    //Create Account Records in Bulk with RequestBody been an Object
    @RequestMapping(method = RequestMethod.POST, value = "/bulk")
    public ResponseEntity<?> createAccounts(@Valid @RequestBody RootObject rootObject, Errors errors) throws JsonProcessingException {

        if (errors.hasErrors()) {
            log.error("Invalid JSON Structure");
        }

//        /** Create the mapper object */
//        ObjectMapper mapper = new ObjectMapper();
//
//        /** Get the string from the object and print it on the console */
//        String string = mapper.writeValueAsString(rootObject);
//        log.info("JSON String: " + string);
//
//        Object obj = validatorUtil.validJson(string, RootObject.class);
//
//        if (obj instanceof ResponseEntity<?>) { //RootObj is valid
//            log.info("received request applying alt new obj");
//            return (ResponseEntity<?>) obj;
//
//        } else {

        log.info("RootObject is Valid");
        ResponseEntity<?> rsp = validatorUtil.validate(rootObject);
        if (rsp != null) {
            log.info("rsp != null");
            return rsp;
        } else {

            List<AccountInModel> list = (List<AccountInModel>) rootObject.getAccounts();
            for (AccountInModel ob : list) {
                ResponseEntity<?> rsps = validatorUtil.validate(ob);
                if (rsps != null) {
                    log.info("rsps != null");
                    return rsps;
                }
            }
            log.info("We're good to go");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"We're good to go!!\"}");

        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/test/bulk", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createS(HttpEntity<String> httpEntity) throws JsonProcessingException {
       //Validate RootObject
        Object obj = validatorUtil.validJson(httpEntity.getBody(), RootObject.class);

        if (obj instanceof ResponseEntity<?>) { //RootObject is invalid
            log.error("RootObject is invalid");
            return (ResponseEntity<?>) obj;
        } else {
            log.info("RootObject is Valid");
            RootObject object = (RootObject) obj;
            ResponseEntity<?> rsp = validatorUtil.validate(object);
            if (rsp != null) {
                log.info("rsp != null");
                return rsp;
            } else {
                List<AccountInModel> list = (List<AccountInModel>) object.getAccounts();
                for (AccountInModel ob : list) {
                    ResponseEntity<?> rsps = validatorUtil.validate(ob);
                    if (rsps != null) {
                        log.info("rsps != null");
                        return rsps;
                    }
                }
                log.info("We're good to go");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"We're good to go!!\"}");
            }
        }
    }


}
