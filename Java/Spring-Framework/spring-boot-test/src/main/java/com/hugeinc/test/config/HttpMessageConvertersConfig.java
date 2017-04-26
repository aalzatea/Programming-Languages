package com.hugeinc.test.config;

import com.hugeinc.test.handlers.JSONHttpMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

/**
 * Created by: HUGE-aalzate
 * Date: 10/5/16
 * Time: 5:35 PM
 */
@Configuration
public class HttpMessageConvertersConfig {

    @Autowired
    private JSONHttpMessageMapper jsonHttpMessageMapper;

    @Bean
    public MappingJackson2HttpMessageConverter customJSONHttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        mappingJackson2HttpMessageConverter.setObjectMapper(jsonHttpMessageMapper.getObjectMapper());

        return mappingJackson2HttpMessageConverter;
    }
}
