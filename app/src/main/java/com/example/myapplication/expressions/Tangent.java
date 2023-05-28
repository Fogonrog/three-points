package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Tangent")
public final class Tangent extends UnaryOperation {
    @JsonCreator
    public Tangent(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) Math.tan(a), "tg");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
