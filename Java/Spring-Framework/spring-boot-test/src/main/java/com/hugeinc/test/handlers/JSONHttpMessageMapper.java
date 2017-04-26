package com.hugeinc.test.handlers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by: HUGE-aalzate
 * Date: 10/5/16
 * Time: 5:37 PM
 */
@Component
public class JSONHttpMessageMapper {

    private final ObjectMapper objectMapper;

    public JSONHttpMessageMapper() {
        objectMapper = new ObjectMapper();
        configObjectMapper();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private void configObjectMapper() {
        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
        objectMapper.setDateFormat(getDateFormat());

        configSerializationFeatures();
        configDeserializationFeatures();
    }

    private DateFormat getDateFormat() {
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance();
        simpleDateFormat.applyPattern(datePattern);

        return simpleDateFormat;
    }

    private void configSerializationFeatures() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);

        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private void configDeserializationFeatures() {
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        objectMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
    }
}
