package com.example.myapplication.expressions;

public final class Sub extends BinaryOperation {
    public Sub(Function left, Function right) {
        super(left, right, (a, b) -> a - b, "-");
    }

    @Override
    public String asString() {
        return "(" + left().asString() + " "
                + symbol() + " "
                + right().asString() + ")";
    }
}
