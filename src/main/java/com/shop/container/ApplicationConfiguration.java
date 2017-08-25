package com.shop.container;

import com.shop.module.common.dao.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by meg on 7/25/17.
 */

//@Configuration
@EnableJpaRepositories(basePackages = "com.shop.module",repositoryBaseClass = BaseRepositoryImpl.class)
@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration {
}
