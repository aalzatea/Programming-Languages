package com.hugeinc.test.business.validations;

import com.hugeinc.test.business.exceptions.BeanValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by aalzate on 10/13/16.
 */
public final class BeanValidator {

    private static final String VALIDATOR_ERROR_MSG = "app.nike.business.bean.validator.error.message";

    public static void validate(Object object) throws BeanValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Object>> validationErrors = validator.validate(object);

        if(!validationErrors.isEmpty()) {
            throw new BeanValidationException(VALIDATOR_ERROR_MSG, getErrors(validationErrors));
        }
    }

    private static List<String> getErrors(Set<ConstraintViolation<Object>> validationErrors) {
        return validationErrors.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}
