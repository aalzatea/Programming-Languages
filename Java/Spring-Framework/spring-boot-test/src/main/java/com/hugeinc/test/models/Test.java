package com.hugeinc.test.models;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by aalzate on 10/12/16.
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 667927416231721751L;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Date date;

    private boolean defaultBoolean;

    private int intDefaultValue;

    private double doubleValue;

    @NotNull(message = "Esta es una prueba")
    private Integer integerValue;

    @NotNull
    private TestTwo testTwo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDefaultBoolean() {
        return defaultBoolean;
    }

    public void setDefaultBoolean(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
    }

    public int getIntDefaultValue() {
        return intDefaultValue;
    }

    public void setIntDefaultValue(int intDefaultValue) {
        this.intDefaultValue = intDefaultValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public TestTwo getTestTwo() {
        return testTwo;
    }

    public void setTestTwo(TestTwo testTwo) {
        this.testTwo = testTwo;
    }
}
