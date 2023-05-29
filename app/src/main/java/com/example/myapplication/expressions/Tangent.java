package com.example.myapplication.expressions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@JsonTypeName("Tangent")
public final class Tangent extends UnaryOperation {
    private String strSingleFunction;

    @JsonCreator
    public Tangent(@JsonProperty("function") Function function,
                   @JsonProperty("strFunction") String strSingleFunction) {
        super(function, (a) -> (float) Math.tan(a), "tg");
        this.strSingleFunction = strSingleFunction;
    }

    public Tangent(Function function) {
        super(function, (a) -> (float) Math.tan(a), "tg");
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
