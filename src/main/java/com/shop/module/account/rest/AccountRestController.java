package com.shop.module.account.rest;

import com.shop.module.account.domain.AccountResponse;
import com.shop.module.common.domain.Result;
import com.shop.module.common.util.ValidationErrorBuilder;
import com.shop.module.common.util.ValidatorUtil;
import com.shop.module.account.domain.AccountRequest;
import com.shop.module.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by meg on 8/14/17.
 */

/*
    Rest layer contains the REST API controllers.
    They link to the service layer via service interfaces.
    A rest layer should never implement any business logic, but just the endpoint mapping, request parameters and security.
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

    //Find Account by accountId
    @RequestMapping(method = RequestMethod.GET, value = "/{accountId}")
    public ResponseEntity<?> findByAccountId(@PathVariable String accountId) {
        AccountResponse account = accountService.findAccountByAccountId(accountId);
        return ResponseEntity.ok(account);
    }

    //Create One Account Record
    @RequestMapping(method = RequestMethod.POST, value = "/add/one")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountRequest am, Errors errors) throws ParseException {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Result result = new Result();
        result.setMessage("success");
        result.setResult(Arrays.asList(accountService.addAccount(am)));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(result);
    }

    //Fetch Last 20 Account Records
    //http://localhost:8081/shop/accounts?limit=5
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findLastWithLimit(@RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        List<AccountResponse> list = accountService.findLastWithLimit(limit);
        Result result = new Result();
        if (list.isEmpty()) {
            log.info("0 Records Found");
            result.setMessage("0 Records Found.");
        } else {
            log.info(list.size() + " Records Found");
            result.setMessage("success");
        }
        result.setResult(list);
        return ResponseEntity.ok(result);
    }



    //Create Account Records in Bulk with RequestBody been an ArrayList
    //To Be Completed
    @RequestMapping(method = RequestMethod.POST, value = "/add/bulk/3", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAccounts(@Valid @RequestBody AccountRequest[] accountRequests, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        for(AccountRequest accountRequest : accountRequests){
            ResponseEntity<?> rsps = validatorUtil.validate(accountRequest);
            if(rsps != null){
                log.info("rsps != null");
                return rsps;
            }
        }

        Result result = new Result();
        result.setMessage("success");
        result.setResult(accountService.addAccounts(accountRequests));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(result);

    }

//    //Create Account Records in Bulk with RequestBody been a string
//    //To Be Completed
//    @RequestMapping(method = RequestMethod.POST, value = "/add/bulk/1", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<?> createAccounts(HttpEntity<String> httpEntity) throws JsonProcessingException {
//        //Validate RootObject
//        Object obj = validatorUtil.validJson(httpEntity.getBody(), RootObject.class);
//
//        if (obj instanceof ResponseEntity<?>) { //RootObject is invalid
//            log.error("RootObject is invalid");
//            return (ResponseEntity<?>) obj;
//        } else {
//            log.info("RootObject is Valid");
//            RootObject object = (RootObject) obj;
//            ResponseEntity<?> rsp = validatorUtil.validate(object);
//            if (rsp != null) {
//                log.info("rsp != null");
//                return rsp;
//            } else {
//                List<AccountRequest> list = (List<AccountRequest>) object.getAccounts();
//                for (AccountRequest ob : list) {
//                    ResponseEntity<?> rsps = validatorUtil.validate(ob);
//                    if (rsps != null) {
//                        log.info("rsps != null");
//                        return rsps;
//                    }
//                }
//                log.info("We're good to go");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"We're good to go!!\"}");
//            }
//        }
//    }
//
//
//
//    //Create Account Records in Bulk with RequestBody been a wrapper Object
//    //To Be Completed
//    @RequestMapping(method = RequestMethod.POST, value = "/add/bulk/2")
//    public ResponseEntity<?> createAccounts(@Valid @RequestBody RootObject rootObject, Errors errors) throws JsonProcessingException {
//
//        if (errors.hasErrors()) {
//            log.error("Invalid JSON Structure");
//        }
//
//        log.info("RootObject is Valid");
//        ResponseEntity<?> rsp = validatorUtil.validate(rootObject);
//        if (rsp != null) {
//            log.info("rsp != null");
//            return rsp;
//        } else {
//
//            List<AccountRequest> list = (List<AccountRequest>) rootObject.getAccounts();
//            for (AccountRequest ob : list) {
//                ResponseEntity<?> rsps = validatorUtil.validate(ob);
//                if (rsps != null) {
//                    log.info("rsps != null");
//                    return rsps;
//                }
//            }
//            log.info("We're good to go");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"We're good to go!!\"}");
//
//        }
//    }



}
