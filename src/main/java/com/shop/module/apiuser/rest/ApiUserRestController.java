package com.shop.module.apiuser.rest;

import com.shop.module.apiuser.domain.ApiUserRequest;
import com.shop.module.apiuser.domain.ApiUserResponse;
import com.shop.module.apiuser.service.ApiUserService;
import com.shop.module.common.domain.Result;
import com.shop.module.common.util.ValidationErrorBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by meg on 8/23/17.
 */

@RestController
@RequestMapping("/apiusers")
@Slf4j
public class ApiUserRestController {

    private final ApiUserService apiUserService;

    @Autowired
    public ApiUserRestController(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    //Create ApiUser
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createApiUser(@Valid @RequestBody ApiUserRequest apiUserRequest, Errors errors){
        if(errors.hasErrors()){
            log.error("createApiUser has " + errors.getErrorCount() + " Errors");
            //ValidationError validationError = ValidationErrorBuilder.fromBindingErrors(errors);
           // return ResponseEntity.badRequest().body(validationError);
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        ApiUserResponse apiUserResponse = apiUserService.createUser(apiUserRequest);
        return  ResponseEntity.status(HttpStatus.CREATED).body(apiUserResponse);
    }

    //Fetch All ApiUser Records
    //http://localhost:8081/shop/apiusers
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> findAll(@RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
        List<ApiUserResponse> list = apiUserService.findAll();
        Result result = new Result();
        if (list.isEmpty()) {
            result.setMessage("0 Records Found.");
        } else {
            result.setMessage("success");
        }
        result.setResult(list);
        return ResponseEntity.ok(result);
    }

    //Find ApiUser by username
    @RequestMapping(method = RequestMethod.GET, value = "/find/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> findByUsername(@PathVariable String username){
        return ResponseEntity.ok().body(apiUserService.findByUsername(username));
    }


}
