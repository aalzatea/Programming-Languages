package com.hugeinc.test.dtos;

import org.dozer.Mapping;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by: HUGE-aalzate
 * Date: 10/6/16
 * Time: 11:41 AM
 */
public class TestDto implements Serializable {

    private static final long serialVersionUID = -2846583532090505841L;

    private String name;

    private String description;

    private Date date;

    @Mapping("defaultBoolean")
    private boolean defaultValue;

    @Mapping("intDefaultValue")
    private int intValue;

    private double doubleValue;

    private Integer integerValue;

    //@Mapping("testTwo")
    private TestTwoDto testTwoDto;

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

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
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

    public TestTwoDto getTestTwoDto() {
        return testTwoDto;
    }

    public void setTestTwoDto(TestTwoDto testTwoDto) {
        this.testTwoDto = testTwoDto;
    }
}
