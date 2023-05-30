package com.example.myapplication.expressions;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Argument")
public final class Argument implements Function {
    public Argument() {
    }

    @Override
    public String asString() {
        return "x";
    }

    @NonNull
    @Override
    public Function clone() throws CloneNotSupportedException {
        return (Function) super.clone();
    }

    @Override
    public float evaluate(float argument) {
        return argument;
    }

    @Override
    public String getStrSingleFunction() {
        return "x";
    }

    @Override
    public void setCurrentFunction(Function function) {
    }
}
