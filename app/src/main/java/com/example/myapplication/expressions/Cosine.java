package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Cosine")
public final class Cosine extends UnaryOperation {
    @JsonCreator
    public Cosine(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) Math.cos(a), "cos");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
