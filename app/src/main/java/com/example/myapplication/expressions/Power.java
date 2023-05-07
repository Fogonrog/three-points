package com.example.myapplication.expressions;

public final class Power extends BinaryOperation {

    public Power(Function left, Function right) {
        super(left, right, (a, b) -> (float) Math.pow(a, b), "^");
    }

    @Override
    public String asString() {
        return "(" + left().asString() + ")"
                + symbol()
                + right().asString();
    }
}
