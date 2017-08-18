package com.shop.accountTransaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.core.Result;
import com.shop.core.errorhandling.ValidationErrorBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by meg on 8/16/17.
 */

@RestController
@RequestMapping("/transactions")
@Slf4j
public class ATransactionRestController {

    private final ATransactionService aTransactionService;

    @Autowired
    public ATransactionRestController(ATransactionService aTransactionService) {
        this.aTransactionService = aTransactionService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@Valid @RequestBody ATransactionInModel am, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        Result result = new Result();
        ATransaction saved = aTransactionService.createATransaction(am);
        result.setMessage("success");
        result.setResult(Arrays.asList(saved));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{accountId}")
    public ResponseEntity<?> transactionsByAcccountAccountId(@PathVariable String accountId){

        Result result = new Result();
        List<ATransaction> list = aTransactionService.fetchATransactionsByAccountAccountId(accountId);

        if(list.isEmpty()){
            result.setMessage("0 records Found");
            return ResponseEntity.ok(result);
        }
        result.setMessage("Success");
        result.setResult(Arrays.asList(list));

        System.out.println(Arrays.toString(list.toArray()));

        return ResponseEntity.ok(result);
    }

}
