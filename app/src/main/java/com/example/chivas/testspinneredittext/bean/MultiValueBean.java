package com.example.chivas.testspinneredittext.bean;

public class MultiValueBean {

    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MultiValueBean(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public MultiValueBean() {
        //
    }
}
