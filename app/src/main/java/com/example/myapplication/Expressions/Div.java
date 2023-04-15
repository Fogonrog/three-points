package com.example.myapplication.Expressions;

public class Div implements Function {
    public Function numerator;
    public Function denominator;

    public Div(Function numerator, Function denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    @Override
    public float evaluate(float x) {
        return numerator.evaluate(x) / denominator.evaluate(x);
    }
}