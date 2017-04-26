package com.hugeinc.test.business.exceptions;

import java.util.List;

/**
 * Created by aalzate on 10/13/16.
 */
public class BeanValidationException extends ApplicationException {

    private List<String> errors;

    public BeanValidationException(String message) {
        super(message);
    }

    public BeanValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
