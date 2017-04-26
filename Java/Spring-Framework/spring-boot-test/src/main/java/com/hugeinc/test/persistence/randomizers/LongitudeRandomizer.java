package com.hugeinc.test.persistence.randomizers;

import io.github.benas.randombeans.api.Randomizer;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by aalzate on 10/21/16.
 */
public class LongitudeRandomizer implements Randomizer<BigDecimal> {

    private double minLongitude;

    private double maxLongitude;

    public LongitudeRandomizer() {

    }

    public LongitudeRandomizer(double minLongitude, double maxLongitude) {
        this.minLongitude = minLongitude;
        this.maxLongitude = maxLongitude;
    }

    @Override
    public BigDecimal getRandomValue() {
        String longitude = null;
        if(minLongitude == 0 || maxLongitude == 0) {
            longitude = io.github.benas.randombeans.randomizers.LongitudeRandomizer.aNewLongitudeRandomizer()
                    .getRandomValue();
        } else {
            double randomValue = minLongitude + (maxLongitude - minLongitude) * new Random().nextDouble();
            longitude = String.valueOf(randomValue);
        }

        return new BigDecimal(longitude);
    }
}
