package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Argument")
public final class Argument implements Function {
    @JsonCreator
    public Argument() {}
    @Override
    public String asString() {
        return "x";
    }

    @Override
    public float evaluate(float argument) {
        return argument;
    }
}
