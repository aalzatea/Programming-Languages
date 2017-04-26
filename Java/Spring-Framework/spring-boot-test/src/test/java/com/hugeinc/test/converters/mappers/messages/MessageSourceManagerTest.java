package com.hugeinc.test.converters.mappers.messages;

import com.hugeinc.test.messages.MessageSourceManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

/**
 * Created by aalzate on 10/13/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageSourceManagerTest {

    @Autowired
    private MessageSourceManager messageSourceManager;

    @Test
    public void testMessageSourceManager() {
        String message = messageSourceManager.getMessage("app.nike.business.bean.validator.error.message");
        String messageLocaleEN = messageSourceManager.getMessage("app.nike.business.bean.validator.error.message", Locale.US);
        String messageLocaleParameterEN = messageSourceManager.getMessage("app.nike.business.bean.validator.error.message.parameter", Locale.US, "Hello");
        String messageLocaleES = messageSourceManager.getMessage("app.nike.business.bean.validator.error.message", new Locale("es"));
        String messageLocaleParameterES = messageSourceManager.getMessage("app.nike.business.bean.validator.error.message.parameter", new Locale("es"), "Hola");
        String nullMessage = messageSourceManager.getMessage(null);
        String emptyMessage = messageSourceManager.getMessage("");
        String messageNotExist = messageSourceManager.getMessage("asdfasdfasdf");

        Assert.assertNotNull(message);
        Assert.assertNotNull(messageLocaleEN);
        Assert.assertNotNull(messageLocaleParameterEN);
        Assert.assertNotNull(messageLocaleES);
        Assert.assertNotNull(messageLocaleParameterES);
        Assert.assertNull(nullMessage);
        Assert.assertNull(emptyMessage);
        Assert.assertEquals("asdfasdfasdf", messageNotExist);
    }
}
