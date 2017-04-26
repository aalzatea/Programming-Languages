package com.hugeinc.test.persistence.randomizers;

import io.github.benas.randombeans.api.Randomizer;

import java.util.Random;

/**
 * Created by aalzate on 10/23/16.
 */
public class MessageRandomizer implements Randomizer<String> {

    private final String[] titles = {
            "Message 1", "Message 2", "Message 3", "Message 4",
            "Message 5", "Message 6", "Message 7", "Message 8",
            "Message 9", "Message 10", "Message 11", "Message 12"
    };

    @Override
    public String getRandomValue() {
        int randomValue = new Random().nextInt(11);
        return titles[randomValue];
    }
}
