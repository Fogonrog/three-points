package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Power")
public final class Power extends BinaryOperation {
    @JsonCreator
    public Power(@JsonProperty("left") Function left,@JsonProperty("right") Function right) {
        super(left, right, (a, b) -> (float) Math.pow(a, b), "^");
    }

    @Override
    public String asString() {
        return "(" + left().asString() + ")"
                + symbol()
                + right().asString();
    }
}
