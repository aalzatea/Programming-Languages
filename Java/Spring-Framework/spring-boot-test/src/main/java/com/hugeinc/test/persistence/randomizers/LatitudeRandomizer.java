package com.hugeinc.test.persistence.randomizers;

import io.github.benas.randombeans.api.Randomizer;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by aalzate on 10/21/16.
 */
public class LatitudeRandomizer implements Randomizer<BigDecimal> {

    private double minLatitude;

    private double maxLatitude;

    public LatitudeRandomizer() {
    }

    public LatitudeRandomizer(double minLatitude, double maxLatitude) {
        this.minLatitude = minLatitude;
        this.maxLatitude = maxLatitude;
    }

    @Override
    public BigDecimal getRandomValue() {
        String latitude = null;
        if(minLatitude == 0 || maxLatitude == 0) {
            latitude = io.github.benas.randombeans.randomizers.LatitudeRandomizer.aNewLatitudeRandomizer()
                    .getRandomValue();
        } else {
            double randomValue = minLatitude + (maxLatitude - minLatitude) * new Random().nextDouble();
            latitude = String.valueOf(randomValue);
        }

        return new BigDecimal(latitude);
    }
}
