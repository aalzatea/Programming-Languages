package com.hugeinc.test.handlers;

import java.io.Serializable;

/**
 * Created by: HUGE-aalzate
 * Date: 10/10/16
 * Time: 2:08 PM
 */
public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = 8594863024450272869L;

    private int code;

    private String message;

    public ErrorMessage() {}

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

