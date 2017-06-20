package com.rest.examples.restexamples.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by miztli on 20/06/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.rest.examples.restexamples.controller.rest"})
@EnableWebMvc
public class ControllerConfig {
    public ControllerConfig() {
        super();
    }

    @Bean
    public CommonsMultipartResolver commonsMultipartResolver(){
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
            commonsMultipartResolver.setMaxUploadSize(100000);
        return commonsMultipartResolver;
    }
}
