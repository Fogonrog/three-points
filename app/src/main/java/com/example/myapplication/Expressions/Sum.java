package com.example.myapplication.Expressions;

public class Sum implements Function {
    public Function numerator;
    public Function denominator;

    public Sum(Function numerator, Function denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public float evaluate(float x) {
        return numerator.evaluate(x) + denominator.evaluate(x);
    }
}
