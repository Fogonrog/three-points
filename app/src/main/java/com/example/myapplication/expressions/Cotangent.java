package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Cotangent")
public final class Cotangent extends UnaryOperation {
    @JsonCreator
    public Cotangent(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) (1.0 / Math.tan(a)), "ctg");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }

}
