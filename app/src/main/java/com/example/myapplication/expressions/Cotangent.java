package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Cotangent")
public final class Cotangent extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Cotangent(@JsonProperty("function") Function function,
                     @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> (float) (1.0 / Math.tan(a)), "ctg");
        this.strSingleFunction = strSingleFunction;
    }

    public Cotangent(Function function) {
        super(function, (a) -> (float) (1.0 / Math.tan(a)), "ctg");
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
