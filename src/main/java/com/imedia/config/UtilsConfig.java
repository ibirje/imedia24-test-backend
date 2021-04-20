package com.imedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.imedia.utils.Utils;
import com.imedia.utils.Validator;

@EnableScheduling
@Configuration
public class UtilsConfig {

    @Bean
    public Validator createValidator() { return new Validator(); }


    @Bean
    public Utils createUtils() { return new Utils(); }
}

