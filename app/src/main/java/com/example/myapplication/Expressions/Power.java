package com.example.myapplication.Expressions;

public class Power implements Function {
    private float power;
    public Function function;
    public Power(Function function, float power) {
        this.function = function;
        this.power = power;
    }
    public float evaluate(float x) {
        return (float) Math.pow(function.evaluate(x),power);
    }
}