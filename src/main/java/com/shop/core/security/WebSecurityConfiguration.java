package com.shop.core.security;

import com.shop.apiuser.ApiUser;
import com.shop.apiuser.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
@ComponentScan(basePackageClasses = DetailsService.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    DetailsService userDetailService;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailService).passwordEncoder(ApiUser.PASSWORD_ENCODER);
        //auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and().httpBasic()
                .and().csrf()
                .disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }



}
