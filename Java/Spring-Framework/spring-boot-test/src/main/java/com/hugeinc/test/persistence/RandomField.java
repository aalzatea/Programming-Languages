package com.hugeinc.test.persistence;

import io.github.benas.randombeans.api.Randomizer;

/**
 * Created by aalzate on 10/21/16.
 */
public class RandomField<T, TC> {

    private String fieldName;

    private Class<T> fieldType;

    private Class<TC> targetClass;

    private Randomizer<T> randomizer;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<T> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<T> fieldType) {
        this.fieldType = fieldType;
    }

    public Class<TC> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<TC> targetClass) {
        this.targetClass = targetClass;
    }

    public Randomizer<T> getRandomizer() {
        return randomizer;
    }

    public void setRandomizer(Randomizer<T> randomizer) {
        this.randomizer = randomizer;
    }
}
