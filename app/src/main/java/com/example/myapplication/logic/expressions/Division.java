package com.example.myapplication.logic.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Division")
public final class Division extends BinaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Division(@JsonProperty("left") Function left,
                    @JsonProperty("right") Function right,
                    @JsonProperty("strFunction") String strSingleFunction) {
        super(left, right, (a, b) -> a / b, "/");
        this.strSingleFunction = strSingleFunction;
    }

    public Division(Function left, Function right) {
        super(left, right, (a, b) -> a / b, "/");
    }

    @Override
    public String asString() {
        return String.format("%s %s %s", left().asString(), symbol(), right().asString());
    }

    public Function copy() throws CloneNotSupportedException {
        return super.clone();
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
