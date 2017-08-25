package com.shop.container;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by meg on 7/25/17.
 */

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackageClasses = MainEntry.class)
public class WebConfiguration {
}
