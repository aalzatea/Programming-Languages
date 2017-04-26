package com.hugeinc.test.converters.mappers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * Created by aalzate on 10/12/16.
 */
@Component
@ApplicationScope
public class DozerMapper {

    @Autowired
    private Mapper mapper;

    public <T> T mapInstance(Object sourceObject, Class<T> destinationObjectClass) {
        return mapper.map(sourceObject, destinationObjectClass);
    }

    public void mapInstance(Object sourceObject, Object destinationObject) {
        mapper.map(sourceObject, destinationObject);
    }
}
