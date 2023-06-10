package com.example.myapplication.logic.expressions;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Num")
public final class Num implements Function {
    private final float number;

    @JsonCreator
    public Num(@JsonProperty("number") float number) {
        this.number = number;
    }

    @Override
    public String asString() {
        if ((int) number == number) {
            return String.valueOf((int) number);
        } else {
            return String.valueOf(number);
        }
    }

    @NonNull
    @Override
    public Function clone() throws CloneNotSupportedException {
        return (Function) super.clone();
    }

    @Override
    public float evaluate(float argument) {
        return this.number;
    }

    @Override
    public String getStrSingleFunction() {
        return String.valueOf(number);
    }

    @Override
    public void setCurrentFunction(Function function) {
    }

    public float getNumber() {
        return number;
    }
}
