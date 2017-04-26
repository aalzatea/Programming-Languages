package com.hugeinc.test.converters.mappers;

import com.hugeinc.test.converters.mappers.utils.BeanFactoryUtil;
import com.hugeinc.test.dtos.TestDto;
import com.hugeinc.test.dtos.TestThreeDto;
import com.hugeinc.test.models.TestThree;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

/**
 * Created by aalzate on 10/12/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DozerMapperTest {

    @Autowired
    private DozerMapper dozerMapper;

    private static com.hugeinc.test.models.Test test;

    private static TestDto testDto;

    @BeforeClass
    public static void setUp() {
        test = BeanFactoryUtil.getTestModel();
        test.setTestTwo(BeanFactoryUtil.getTestTwoModel());

        testDto = BeanFactoryUtil.getTestDto();
        testDto.setTestTwoDto(BeanFactoryUtil.getTestTwoDto());
    }

    @AfterClass
    public static void cleanUp() {
        test = null;
        testDto = null;
    }

    @Test
    public void testConvertDtoToModel() {
        com.hugeinc.test.models.Test test = dozerMapper.mapInstance(testDto, com.hugeinc.test.models.Test.class);

        assertEquals(testDto.getDate(), test.getDate());
        assertEquals(testDto.getDescription(), test.getDescription());
        assertEquals(testDto.getDoubleValue(), test.getDoubleValue(), 0);
        assertEquals(testDto.getIntegerValue(), test.getIntegerValue());
        assertEquals(testDto.getIntValue(), test.getIntDefaultValue());
        assertEquals(testDto.getName(), test.getName());
        //assertEquals(testDto.getTestTwoDto().getDate(), test.getTestTwo().getDate());
        //assertEquals(testDto.getTestTwoDto().getInteger(), test.getTestTwo().getInteger());
        assertEquals(testDto.getTestTwoDto().getTestName(), test.getTestTwo().getTestName());

    }

    @Test
    public void testConvertModelToDto() {
        TestDto testDto = dozerMapper.mapInstance(test, TestDto.class);

        assertEquals(test.getDate(), testDto.getDate());
        assertEquals(test.getDescription(), testDto.getDescription());
        assertEquals(test.getDoubleValue(), testDto.getDoubleValue(), 0);
        assertEquals(test.getIntegerValue(), testDto.getIntegerValue());
        assertEquals(test.getIntDefaultValue(), testDto.getIntValue());
        assertEquals(test.getName(), testDto.getName());
        //assertEquals(test.getTestTwo().getDate(), testDto.getTestTwoDto().getDate());
        //assertEquals(test.getTestTwo().getInteger(), testDto.getTestTwoDto().getInteger());
        assertEquals(test.getTestTwo().getTestName(), testDto.getTestTwoDto().getTestName());

    }

    @Test
    public void testConvertModelToDtoThree() {
        TestThree testThree = BeanFactoryUtil.getTestThreeModel();
        TestThreeDto testThreeDto = dozerMapper.mapInstance(testThree, TestThreeDto.class);

        assertEquals(testThree.getName(), testThreeDto.getName());
        //assertThat(testThreeDto.getValues(), containsInAnyOrder("Value 1", "Value 2", "Value 3"));
        assertThat(testThreeDto.getValues(), containsInAnyOrder(testThree.getValues().toArray(new String[testThree.getValues().size()])));
        assertThat(testThreeDto.getMap(), is(testThree.getMap()));
    }
}
