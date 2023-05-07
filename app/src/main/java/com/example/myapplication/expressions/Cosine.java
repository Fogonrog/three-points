package com.example.myapplication.expressions;

public final class Cosine extends UnaryOperation {

    public Cosine(Function function) {
        super(function, (a) -> (float) Math.cos(a), "cos");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }
}
