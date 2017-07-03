package com.aalzatea.springboot.examples.model.websockets;

import java.io.Serializable;

/**
 * Created by aalzate on 7/3/17.
 */
public class HelloMessage implements Serializable {

    private static final long serialVersionUID = 4314926395135500764L;

    private String name;

    public HelloMessage() {

    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
