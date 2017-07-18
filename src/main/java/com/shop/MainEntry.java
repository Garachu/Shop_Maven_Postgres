package com.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Created by meg on 7/15/17.
 */

@SpringBootApplication
public class MainEntry {

    private  static final Logger log = LoggerFactory.getLogger(MainEntry.class);

    public static void main(String args[]) {
        SpringApplication.run(MainEntry.class, args);
    }

//    @Bean
//    CommandLineRunner init(AccountRepository accountRepository,
//                           BookMarkRepository bookmarkRepository) {
//        return (evt) -> Arrays.asList(
//                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//                .forEach(
//                        a -> {
//                            Account account = accountRepository.save(new Account(a,
//                                    "password"));
//                            bookmarkRepository.save(new BookMark(account,
//                                    "http://bookmark.com/1/" + a, "A description"));
//                            bookmarkRepository.save(new BookMark(account,
//                                    "http://bookmark.com/2/" + a, "A description"));
//                        });
//    }


}
