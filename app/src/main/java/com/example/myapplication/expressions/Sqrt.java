package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sqrt")
public final class Sqrt extends UnaryOperation {
    @JsonCreator
    public Sqrt(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) Math.sqrt(a), "√");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
