package com.hugeinc.test.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by aalzate on 10/13/16.
 */
@Component
public final class MessageSourceManager {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return getMessage(code, null, null);
    }

    public String getMessage(String code, Locale locale) {
        return getMessage(code, locale, null);
    }

    public String getMessage(String code, Locale locale, Object... parameters) {
        if(StringUtils.isEmpty(code)) {
            return null;
        }

        if(locale == null) {
            locale = Locale.US;
        }

        if(parameters == null) {
            parameters = Collections.emptyList().toArray();
        }

        return Optional.of(messageSource.getMessage(code, parameters, locale)).orElse(code);
    }
}
