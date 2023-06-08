package com.example.myapplication.logic.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Power")
public final class Power extends BinaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Power(@JsonProperty("left") Function left,
                 @JsonProperty("right") Function right,
                 @JsonProperty("strFunction") String strSingleFunction) {
        super(left, right, (a, b) -> (float) Math.pow(a, b), "^");
        this.strSingleFunction = strSingleFunction;
    }

    public Power(Function left, Function right) {
        super(left, right, (a, b) -> (float) Math.pow(a, b), "^");
    }

    public Function copy() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String asString() {
        return String.format("(%s)%s%s", left().asString(), symbol(), right().asString());
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
