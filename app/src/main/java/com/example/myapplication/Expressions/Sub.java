package com.example.myapplication.Expressions;

public class Sub implements Function {
    public Function left;
    public Function right;

    public Sub(Function left, Function right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public float evaluate(float x) {
        return left.evaluate(x) - right.evaluate(x);
    }
}