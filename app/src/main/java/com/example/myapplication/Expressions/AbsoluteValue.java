package com.example.myapplication.Expressions;

public final class AbsoluteValue extends UnaryOperation {
    @Override
    public String asString(){
        return symbol + function.asString() + symbol;
    }
    public AbsoluteValue(Function function) {
        super(function, (a) -> (float) Math.abs(a), "|");
    }
}