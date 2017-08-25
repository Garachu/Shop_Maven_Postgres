package com.shop.container.security;

import com.shop.module.apiuser.domain.ApiUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by meg on 8/2/17.
 */

/*
    The configuration creates a Servlet Filter known as the springSecurityFilterChain which is responsible for all the security
    (protecting the application URLs, validating submitted username and passwords, redirecting to the log in form, etc)
    within your application.
 */

@Configuration //lets Spring know that this file contains configuration information.
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserDetailsService userDetailService;


    /*
        The name of the configureGlobal method is not important.
        it is important to only configure AuthenticationManagerBuilder in a class annotated with either
        @EnableWebSecurity, @EnableGlobalMethodSecurity, or @EnableGlobalAuthentication
        Doing otherwise has unpredictable results.
     */

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailService).passwordEncoder(ApiUser.PASSWORD_ENCODER);
    }

//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.info("configureGlobal");
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("meg").password("password").roles("USER", "ADMIN");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("**/users").hasRole("ADMIN")
                .anyRequest() //authorize all the requests
                .fullyAuthenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
