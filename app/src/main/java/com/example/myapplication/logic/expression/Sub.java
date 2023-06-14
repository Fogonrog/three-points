package com.example.myapplication.logic.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sub")
public final class Sub extends BinaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Sub(@JsonProperty("left") Function left,
               @JsonProperty("right") Function right,
               @JsonProperty("strFunction") String strSingleFunction) {
        super(left, right, (a, b) -> a - b, "-");
        this.strSingleFunction = strSingleFunction;
    }

    public Sub(Function left, Function right) {
        super(left, right, (a, b) -> a - b, "-");
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
