package com.example.myapplication.expressions;

public final class Sinus extends UnaryOperation {

    public Sinus(Function function) {
        super(function, (a) -> (float) Math.sin(a), "sin");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
