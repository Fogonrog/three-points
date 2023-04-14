package com.example.myapplication.Expressions;

public class Sqrt implements Function {
    public Function function;
    public Sqrt(Function func) {
        this.function = func;
    }
    public float evaluate(float x) {
        return (float) Math.sqrt(function.evaluate(x));
    }
}