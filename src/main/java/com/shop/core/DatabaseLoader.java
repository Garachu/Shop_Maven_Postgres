package com.shop.core;

import com.shop.apiuser.ApiUser;
import com.shop.apiuser.ApiUserRepository;
import com.shop.product.Product;
import com.shop.product.ProductService;
import com.shop.sale.Sale;
import com.shop.sale.SaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by meg on 7/11/17.
 */

@Component
public class DatabaseLoader implements ApplicationRunner{

    private final ApiUserRepository apiUserRepository;

    public static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();


    private  static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    public DatabaseLoader(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }


    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String[] roles = new String[]{"USER", "ADMIN"};

        ApiUser me = new ApiUser(
                "meg",
                PASSWORD_ENCODER.encode("password"),
               // "password",
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Arrays.asList(roles).toString(),
                "megngarachu@gmail.com"
        );

        apiUserRepository.save(me);

        ApiUser joel = new ApiUser(
                "joel",
                PASSWORD_ENCODER.encode("password"),
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Boolean.TRUE,
                Arrays.asList(roles).toString(),
                "megngarachu@gmail.com"
        );

        apiUserRepository.save(joel);

    }
}
