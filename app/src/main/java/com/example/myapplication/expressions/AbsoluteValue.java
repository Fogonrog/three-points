package com.example.myapplication.expressions;

public final class AbsoluteValue extends UnaryOperation {

    public AbsoluteValue(Function function) {
        super(function, (a) -> (float) Math.abs(a), "|");
    }

    @Override
    public String asString() {
        return getSymbol() + getFunction().asString() + getSymbol();
    }
}
