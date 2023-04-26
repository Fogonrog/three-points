package com.example.myapplication.Expressions;

public final class Sub extends BinaryOperation {
    public Sub(Function left, Function right) {
        super(left, right, (a, b) -> a - b, "-");
    }
}
