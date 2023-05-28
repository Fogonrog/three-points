package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sub")
public final class Sub extends BinaryOperation {
    @JsonCreator
    public Sub(@JsonProperty("left") Function left, @JsonProperty("right") Function right) {
        super(left, right, (a, b) -> a - b, "-");
    }

    @Override
    public String asString() {
        return "(" + left().asString() + " "
                + symbol() + " "
                + right().asString() + ")";
    }
}
