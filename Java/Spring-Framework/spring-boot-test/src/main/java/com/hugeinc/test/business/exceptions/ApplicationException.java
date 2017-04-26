package com.hugeinc.test.business.exceptions;

/**
 * Created by aalzate on 10/13/16.
 */
public class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
