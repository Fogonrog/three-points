package com.example.myapplication.logic.expression;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Sqrt")
public final class Sqrt extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Sqrt(@JsonProperty("function") Function function,
                @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> (float) Math.sqrt(a), "√");
        this.strSingleFunction = strSingleFunction;
    }

    public Sqrt(Function function) {
        super(function, (a) -> (float) Math.sqrt(a), "√");
    }
    public Function copy() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }

    public void setCurrentFunction(Function function) {
        setFunction(function);
    }
    @Override
    public String getStrSingleFunction() {
        return strSingleFunction;
    }
}
