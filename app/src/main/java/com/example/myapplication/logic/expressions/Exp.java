package com.example.myapplication.logic.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Exp")
public final class Exp extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Exp(@JsonProperty("function") Function function,
                @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> (float) Math.sqrt(a), "exp");
        this.strSingleFunction = strSingleFunction;
    }

    public Exp(Function function) {
        super(function, (a) -> (float) Math.exp(a), "exp");
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
