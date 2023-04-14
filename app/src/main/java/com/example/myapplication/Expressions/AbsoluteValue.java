package com.example.myapplication.Expressions;

public class AbsoluteValue implements Function {
    public Function function;
    public AbsoluteValue(Function func) {
        this.function = func;
    }
    @Override
    public float evaluate(float x) {
        return Math.abs(function.evaluate(x));
    }
}