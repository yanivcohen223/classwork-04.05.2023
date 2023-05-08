package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Order implements Serializable {

    public Order() {
    }

    public Order(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id;

    @JsonProperty(value = "name")
    public String name;
}
