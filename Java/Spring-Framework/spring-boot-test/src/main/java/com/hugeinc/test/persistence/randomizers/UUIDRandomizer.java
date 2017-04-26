package com.hugeinc.test.persistence.randomizers;

import io.github.benas.randombeans.api.Randomizer;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by aalzate on 10/21/16.
 */
public class UUIDRandomizer implements Randomizer<String> {

    private String defaultValue;

    public UUIDRandomizer() {

    }

    public UUIDRandomizer(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getRandomValue() {
        if(StringUtils.isEmpty(defaultValue))
            return UUID.randomUUID().toString();
        else
            return defaultValue;
    }
}
