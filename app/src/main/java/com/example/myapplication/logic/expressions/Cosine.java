package com.example.myapplication.logic.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Cosine")
public final class Cosine extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Cosine(@JsonProperty("function") Function function,
                  @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> (float) Math.cos(a), "cos");
        this.strSingleFunction = strSingleFunction;
    }

    public Cosine(Function function) {
        super(function, (a) -> (float) Math.cos(a), "cos");
    }

    public Function copy() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
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
