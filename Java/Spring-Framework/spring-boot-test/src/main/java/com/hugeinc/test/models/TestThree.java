package com.hugeinc.test.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by aalzate on 10/12/16.
 */
public class TestThree implements Serializable {

    private static final long serialVersionUID = 7348926746307352289L;

    private String name;

    private Map<Integer, String> map;

    private List<String> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
