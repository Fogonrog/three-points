package com.example.myapplication.logic.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sum")
public final class Sum extends BinaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Sum(@JsonProperty("left") Function left,
               @JsonProperty("right") Function right,
               @JsonProperty("strFunction") String strSingleFunction) {
        super(left, right, (a, b) -> a + b, "+");
        this.strSingleFunction = strSingleFunction;
    }

    public Sum(Function left, Function right) {
        super(left, right, (a, b) -> a + b, "+");
    }

    public Function copy() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String asString() {
        return String.format("(%s %s %s)", left().asString(), symbol(), right().asString());
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
