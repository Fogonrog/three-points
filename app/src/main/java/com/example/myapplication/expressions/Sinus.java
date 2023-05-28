package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sinus")
public final class Sinus extends UnaryOperation {
    @JsonCreator
    public Sinus(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) Math.sin(a), "sin");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
