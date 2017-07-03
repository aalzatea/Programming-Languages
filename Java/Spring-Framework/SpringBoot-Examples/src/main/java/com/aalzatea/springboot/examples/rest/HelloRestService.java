package com.aalzatea.springboot.examples.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by aalzate on 7/3/17.
 */
@RestController
public class HelloRestService {

    @RequestMapping("/rest")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
