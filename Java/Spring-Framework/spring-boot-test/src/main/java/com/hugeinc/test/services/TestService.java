package com.hugeinc.test.services;

import com.hugeinc.test.dtos.TestDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by: HUGE-aalzate
 * Date: 10/6/16
 * Time: 11:13 AM
 */
@RestController
@RequestMapping("test")
public class TestService {

    @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public TestDto testService() {
        TestDto testDto = new TestDto();
        testDto.setDescription("Description");
        testDto.setDate(new Date());
        testDto.setDefaultValue(true);
        testDto.setDoubleValue(5d);
        testDto.setIntegerValue(null);
        testDto.setIntValue(35);
        testDto.setName("Name");

        return testDto;
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public TestDto testServiceException() {
        throw new UnsupportedOperationException("Esto es una prueba de excepcion");
    }
}