package com.shop.container.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.validation.Validator;


/**
 * Created by meg on 7/19/17.
 */

@Configuration
public class RestConfiguration extends RepositoryRestConfigurerAdapter{

    @Autowired
    private Validator globalValidator;

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", globalValidator);
        validatingListener.addValidator("beforeSave", globalValidator);
    }

}
