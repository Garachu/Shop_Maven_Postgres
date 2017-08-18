package com.shop.apiuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by meg on 8/2/17.
 */

@Component
public class DetailsService implements UserDetailsService{

    private final ApiUserRepository apiUserRepository;

    private  static final Logger log = LoggerFactory.getLogger(DetailsService.class);

    @Autowired
    public DetailsService(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        ApiUser apiUser = apiUserRepository.findByUsername(s).orElseThrow(
                () ->  new UsernameNotFoundException("jjjj")
        );

        log.info("api username: " + apiUser.getUsername());
        log.info("api password: " + apiUser.getPassword());
        log.info("api roles: " + apiUser.getRoles());



        return new org.springframework.security.core.userdetails.User(
                apiUser.getUsername(),
                apiUser.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(apiUser.getRoles().toString()));
    }
}
