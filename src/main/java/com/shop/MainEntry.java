package com.shop;

import com.shop.core.BaseRepositoryFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

/**
 * Created by meg on 7/15/17.
 */


//@SpringBootApplication activate autoconfiguration, component scanning, and also allows bean definitions.
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class MainEntry {

    private  static final Logger log = LoggerFactory.getLogger(MainEntry.class);

    public static void main(String args[]) {
        SpringApplication.run(MainEntry.class, args);
    }

}
