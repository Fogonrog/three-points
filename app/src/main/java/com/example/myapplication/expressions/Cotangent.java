package com.example.myapplication.expressions;

public final class Cotangent extends UnaryOperation {
    public Cotangent(Function function) {
        super(function, (a) -> (float) (1.0 / Math.tan(a)), "ctg");
    }

    @Override
    public String asString() {
        return getSymbol() + "(" + getFunction().asString() + ")";
    }

}
