package com.example.myapplication.expressions;

public final class Sum extends BinaryOperation {
    public Sum(Function left, Function right) {
        super(left, right, (a, b) -> a + b, "+");
    }

    @Override
    public String asString() {
        return "(" + getLeft().asString() + " "
                + getSymbol() + " "
                + getRight().asString() + ")";
    }
}
