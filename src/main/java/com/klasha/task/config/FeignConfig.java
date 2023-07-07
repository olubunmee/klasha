package com.klasha.task.config;

import feign.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class FeignConfig {


    @Bean
    Logger.Level feignLoggerLevel() {
        if (log.isDebugEnabled()) return Logger.Level.FULL;
        return Logger.Level.BASIC;
    }
}