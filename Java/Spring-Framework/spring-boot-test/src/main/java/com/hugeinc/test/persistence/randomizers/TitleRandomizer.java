package com.hugeinc.test.persistence.randomizers;

import io.github.benas.randombeans.api.Randomizer;

import java.util.Random;

/**
 * Created by aalzate on 10/23/16.
 */
public class TitleRandomizer implements Randomizer<String> {

    private final String[] titles = {
        "Title 1", "Title 2", "Title 3", "Title 4",
        "Title 5", "Title 6", "Title 7", "Title 8",
        "Title 9", "Title 10", "Title 11", "Title 12"
    };

    @Override
    public String getRandomValue() {
        int randomValue = new Random().nextInt(11);
        return titles[randomValue];
    }
}
