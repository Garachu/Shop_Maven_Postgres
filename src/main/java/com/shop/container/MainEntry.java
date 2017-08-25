package com.shop.container;

import com.shop.module.common.dao.BaseRepositoryFactoryBean;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.servlet.annotation.MultipartConfig;

/**
 * Created by meg on 7/15/17.
 */


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.shop.module", repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EntityScan(basePackages = "com.shop.module")
@ComponentScan(basePackages = "com.shop")
//@EnableCaching
public class MainEntry {

    public static void main(String args[]) {
        SpringApplication.run(MainEntry.class, args);
    }

//    @Bean
//    public CacheManager cacheManager(){
//        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("accounts");
//        return cacheManager;
//    }

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

}
