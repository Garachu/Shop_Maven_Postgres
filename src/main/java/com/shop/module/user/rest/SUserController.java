package com.shop.module.user.rest;

import com.shop.module.common.domain.Result;
import com.shop.module.common.util.ValidationErrorBuilder;
import com.shop.module.user.dao.SUserRepository;
import com.shop.module.user.SUserResource;
import com.shop.module.user.domain.SUser;
import com.shop.module.user.domain.SUserResponse;
import com.shop.module.user.service.SUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 7/28/17.
 */

@RestController
@RequestMapping("users")
@Slf4j
public class SUserController {

    private final SUserService sUserService;

    @Autowired
    public SUserController(SUserService sUserService){
        this.sUserService = sUserService;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody SUser user, Errors errors){
        if(errors.hasErrors()){
            return  ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        Result result = new Result();
        result.setMessage("success");
        result.setResult(Arrays.asList(sUserService.createUser(user)));
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(result);
    }

    //http://localhost:8081/shop/users?limit=5
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers(@RequestParam(value = "limit", required = false, defaultValue = "20") int limit){
        List<SUserResponse> list = sUserService.getUsers(limit);
        Result result = new Result();
        if (list.isEmpty()) {
           result.setMessage("0 Records Found.");
        } else {
            result.setMessage("success");
        }
        result.setResult(list);
        return ResponseEntity.ok(result);

    }







    //@RequestMapping(method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ADMIN')")
//    public Resources<SUserResource> getApplicationUsers(@AuthenticationPrincipal final User user){
//        log.info("UserDetails username: " + user.getUsername());
//        log.info("UserDetails password: " + user.getPassword());
//        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//        authorities.forEach(a ->
//        log.info("Authorities:  " + a.toString()));
//
//
//        List<SUser> users = sUserRepository.findAll();
//        List<SUserResource> sUserResources = new ArrayList<>();
//
//        if(users.isEmpty()){
//
//        }else{
//            List<SUserResponse> list = users.stream().map(u -> new SUserResponse(u.getFullname(), u.getUsername())).collect(Collectors.toList());
//            sUserResources = list.stream().map(SUserResource::new).collect(Collectors.toList());
//        }
//
//        return new Resources<>(sUserResources);
//
//    }
//


}
