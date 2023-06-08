package com.example.myapplication.logic.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("AbsoluteValue")
public final class AbsoluteValue extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public AbsoluteValue(@JsonProperty("function") Function function,
                         @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> Math.abs(a), "|");
        this.strSingleFunction = strSingleFunction;
    }

    public AbsoluteValue(Function function) {
        super(function, (a) -> Math.abs(a), "|");
    }
    @Override
    public String asString() {
        return getSymbol() + getFunction().asString() + getSymbol();
    }
    @Override
    public void setCurrentFunction(Function function) {
        setFunction(function);
    }
    @Override
    public String getStrSingleFunction() {
        return strSingleFunction;
    }
}
