package com.hugeinc.test.converters.mappers;

import com.hugeinc.test.dtos.TestTwoDto;
import com.hugeinc.test.models.TestTwo;
import org.dozer.DozerConverter;
import org.dozer.MappingException;

/**
 * Created by aalzate on 10/12/16.
 */
public class TestTwoConverter extends DozerConverter<TestTwo, TestTwoDto> {

    /**
     * Defines two types, which will take part transformation.
     * As Dozer supports bi-directional mapping it is not known which of the classes is source and
     * which is destination. It will be decided in runtime.
     *
     */
    public TestTwoConverter() {
        this(TestTwo.class, TestTwoDto.class);
    }

    /**
     * Defines two types, which will take part transformation.
     * As Dozer supports bi-directional mapping it is not known which of the classes is source and
     * which is destination. It will be decided in runtime.
     *
     * @param prototypeA type one
     * @param prototypeB type two
     */
    public TestTwoConverter(Class<TestTwo> prototypeA, Class<TestTwoDto> prototypeB) {
        super(prototypeA, prototypeB);
    }

    @Override
    public TestTwoDto convertTo(TestTwo source, TestTwoDto destination) {
        if(source == null) {
            return null;
        }

        TestTwoDto testTwoDto = null;
        if (destination == null) {
            testTwoDto = new TestTwoDto();
        } else {
            testTwoDto = destination;
        }

        testTwoDto.setTestName(source.getTestName());

        return testTwoDto;
    }

    @Override
    public TestTwo convertFrom(TestTwoDto source, TestTwo destination) {
        if(source == null) {
            return null;
        }

        TestTwo testTwo = null;
        if (destination == null) {
            testTwo = new TestTwo();
        } else {
            testTwo = destination;
        }

        testTwo.setTestName(source.getTestName());

        return testTwo;
    }
}
