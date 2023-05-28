package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("AbsoluteValue")
public final class AbsoluteValue extends UnaryOperation {
    @JsonCreator
    public AbsoluteValue(@JsonProperty("function") Function function) {
        super(function, (a) -> (float) Math.abs(a), "|");
    }

    @Override
    public String asString() {
        return getSymbol() + getFunction().asString() + getSymbol();
    }
}
