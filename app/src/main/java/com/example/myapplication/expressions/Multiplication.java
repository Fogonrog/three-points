package com.example.myapplication.expressions;

public final class Multiplication extends BinaryOperation {
    public Multiplication(Function left, Function right) {
        super(left, right, (a, b) -> a * b, "×");
    }

    @Override
    public String asString() {
        return left().asString() + " "
                + symbol() + " "
                + right().asString();
    }
}
