package com.example.myapplication.expressions;

public final class Sqrt extends UnaryOperation {

    public Sqrt(Function function) {
        super(function, (a) -> (float) Math.sqrt(a), "√");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
