package com.example.myapplication.Expressions;

public class Sum implements Function {
    public Function left;
    public Function right;

    public Sum(Function left, Function right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public float evaluate(float x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}
