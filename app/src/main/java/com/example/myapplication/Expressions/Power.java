package com.example.myapplication.Expressions;

public class Power implements Function {
    public float power;
    public Function function;
    public Power(Function function, float power) {
        this.function = function;
        this.power = power;
    }
    @Override
    public float evaluate(float x) {
        return (float) Math.pow(function.evaluate(x),power);
    }
}