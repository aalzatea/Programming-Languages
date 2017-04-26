package com.hugeinc.test.messages.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by aalzate on 10/13/16.
 */
@Configuration
public class MessageSourceConfig {

    private final int CACHE_REFRESH_EACH_TWELVE_HOURS = 43200;

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setConcurrentRefresh(true);
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(CACHE_REFRESH_EACH_TWELVE_HOURS);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }
}
