package com.shop.user;

import com.shop.core.errorhandling.ValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 7/28/17.
 */

@RestController
@RequestMapping("users")
public class SUserController {

    private final SUserRepository sUserRepository;

    @Autowired
    public SUserController(SUserRepository sUserRepository){
        this.sUserRepository = sUserRepository;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createSystemUser(@Valid @RequestBody SUser user, Errors errors){
        if(errors.hasErrors()){
            return  ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        return  ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    Resources<SUserResource> getApplicationUsers(){
        List<SUser> users = sUserRepository.findAll();
        List<SUserResource> sUserResources = new ArrayList<>();

        if(users.isEmpty()){

        }else{
            List<SUserOutModel> list = users.stream().map(u -> new SUserOutModel(u.getFullname(), u.getUsername())).collect(Collectors.toList());
            sUserResources = list.stream().map(SUserResource::new).collect(Collectors.toList());
        }

        return new Resources<>(sUserResources);

    }
}
