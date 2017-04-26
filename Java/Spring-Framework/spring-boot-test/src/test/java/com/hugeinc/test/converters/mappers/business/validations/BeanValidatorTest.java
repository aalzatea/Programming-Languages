package com.hugeinc.test.converters.mappers.business.validations;

import com.hugeinc.test.business.exceptions.BeanValidationException;
import com.hugeinc.test.business.validations.BeanValidator;
import com.hugeinc.test.converters.mappers.utils.BeanFactoryUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by aalzate on 10/13/16.
 */
public class BeanValidatorTest {

    @Test(expected = BeanValidationException.class)
    public void testBeanValidator() throws BeanValidationException {
        com.hugeinc.test.models.Test test = BeanFactoryUtil.getTestModel();
        BeanValidator.validate(test);
    }

    @Test
    public void testBeanValidatorCatchException() {
        com.hugeinc.test.models.Test test = BeanFactoryUtil.getTestModel();

        try {
            BeanValidator.validate(test);
        } catch (BeanValidationException e) {
            e.getErrors().stream().forEach(System.out::println);
            Assert.assertTrue(!e.getErrors().isEmpty());
        }
    }
}
