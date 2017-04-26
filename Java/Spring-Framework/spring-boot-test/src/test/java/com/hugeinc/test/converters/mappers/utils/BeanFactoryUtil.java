package com.hugeinc.test.converters.mappers.utils;

import com.hugeinc.test.dtos.TestDto;
import com.hugeinc.test.dtos.TestThreeDto;
import com.hugeinc.test.dtos.TestTwoDto;
import com.hugeinc.test.models.Test;
import com.hugeinc.test.models.TestThree;
import com.hugeinc.test.models.TestTwo;

import java.util.*;

/**
 * Created by aalzate on 10/12/16.
 */
public final class BeanFactoryUtil {

    public static Test getTestModel() {
        Test test = new Test();
        test.setDescription("Test Description");
        test.setDate(new Date());
        test.setDefaultBoolean(true);
        test.setDoubleValue(5d);
        test.setIntegerValue(null);
        test.setIntDefaultValue(36);
        test.setName("Test Name");

        return test;
    }

    public static TestDto getTestDto() {
        TestDto testDto = new TestDto();
        testDto.setDescription("DTO Description");
        testDto.setDate(new Date());
        testDto.setDefaultValue(true);
        testDto.setDoubleValue(5d);
        testDto.setIntegerValue(null);
        testDto.setIntValue(35);
        testDto.setName("DTO Name");

        return testDto;
    }

    public static TestTwo getTestTwoModel() {
        TestTwo testTwo = new TestTwo();
        testTwo.setDate(new Date());
        testTwo.setInteger(12);
        testTwo.setTestName("Test Two Name");

        return testTwo;
    }

    public static TestTwoDto getTestTwoDto() {
        TestTwoDto testTwoDto = new TestTwoDto();
        testTwoDto.setDate(new Date());
        testTwoDto.setInteger(13);
        testTwoDto.setTestName("Test Two Dto Name");

        return testTwoDto;
    }

    public static TestThree getTestThreeModel() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Value 0");
        map.put(1, "Value 1");

        List<String> values = Arrays.asList("Value 1", "Value 2", "Value 3");

        TestThree testThree = new TestThree();
        testThree.setMap(map);
        testThree.setName("Test Three Name");
        testThree.setValues(values);

        return testThree;
    }

    public static TestThreeDto getTestThreeDto() {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "Value 0");
        map.put(1, "Value 1");

        List<String> values = Arrays.asList("Value 1", "Value 2", "Value 3");

        TestThreeDto testThreeDto = new TestThreeDto();
        testThreeDto.setMap(map);
        testThreeDto.setName("Test Three Name");
        testThreeDto.setValues(values);

        return testThreeDto;
    }
}
