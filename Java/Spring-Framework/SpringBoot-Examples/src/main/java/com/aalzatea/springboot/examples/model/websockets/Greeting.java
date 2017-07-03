package com.aalzatea.springboot.examples.model.websockets;

import java.io.Serializable;

/**
 * Created by aalzate on 7/3/17.
 */
public class Greeting implements Serializable {

    private static final long serialVersionUID = 6045463870745418927L;

    private String content;

    public Greeting() {

    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
