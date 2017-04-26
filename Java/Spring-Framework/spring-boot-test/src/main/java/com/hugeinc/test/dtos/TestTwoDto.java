package com.hugeinc.test.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by aalzate on 10/12/16.
 */
public class TestTwoDto implements Serializable {

    private static final long serialVersionUID = 2148549652493477093L;

    private String testName;

    private Date date;

    private Integer integer;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
