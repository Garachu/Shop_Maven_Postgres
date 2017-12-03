package com.shop.container.security;

import com.shop.module.apiuser.domain.ApiUser;
import com.shop.module.apiuser.dao.ApiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * Created by meg on 8/2/17.
 */

/*
    UserDetailsService interface is used in order to lookup the username, password and GrantedAuthorities for any given user.
    UserDetailsService interface provide only one method which implementing class need to implement.
 */

@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService{

    private final ApiUserRepository apiUserRepository;

    @Autowired
    public CustomUserDetailsService(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }


    /*
        UserDetailsService provides a method loadUserByUsername() in which we pass username obtained from login page and it returns UserDetails
        UserDetailsService needs username, password and role for the given username
        While authentication, spring security calls this method passing username obtained from login page.
        UserDetails is container for core user information.
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApiUser apiUser = apiUserRepository.findByUsername(username).orElseThrow(
                () ->  new UsernameNotFoundException("jjjj")
        );

        log.info("api username: " + apiUser.getUsername());
        log.info("api password: " + apiUser.getPassword());
        log.info("api roles: " + apiUser.getRoles());

        //roles are saved as String comma separated i,e  String roles = "ADMIN, USER";
        String strRoles = apiUser.getRoles();

        //Split the comma separated list of roles to an Array
        List<String> roles = Arrays.asList(strRoles.split("\\s*,\\s*"));

        return new org.springframework.security.core.userdetails.User(
                apiUser.getUsername(),
                apiUser.getPassword(),
                grantedAuthorities(roles));


    }


    //GrantedAuthority is an interface
    //SimpleGrantedAuthority implements GrantedAuthority
    //SimpleGrantedAuthority(String role) constructor takes in a String as argument
    private static Collection<GrantedAuthority> grantedAuthorities(List<String> list) {
        final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : list) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return grantedAuthorities;
    }
}
