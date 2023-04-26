package com.example.myapplication.Expressions;

public final class Sum extends BinaryOperation {
    public Sum(Function left, Function right) {
        super(left, right, (a, b) -> a + b, "+");
    }
}
