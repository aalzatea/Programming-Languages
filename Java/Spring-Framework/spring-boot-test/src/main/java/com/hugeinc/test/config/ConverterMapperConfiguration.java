package com.hugeinc.test.config;

import com.hugeinc.test.converters.mappers.TestTwoConverter;
import com.hugeinc.test.dtos.TestDto;
import com.hugeinc.test.models.Test;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aalzate on 10/12/16.
 */
@Configuration
public class ConverterMapperConfiguration {

    @Bean
    public Mapper configDozerMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.addMapping(beanMappingBuilder());
        return dozerBeanMapper;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {

            @Override
            protected void configure() {
                mapping(Test.class, TestDto.class)
                        .fields("testTwo", "testTwoDto", FieldsMappingOptions.customConverter(TestTwoConverter.class));
            }
        };
    }
}
