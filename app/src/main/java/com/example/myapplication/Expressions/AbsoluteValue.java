package com.example.myapplication.Expressions;

public final class AbsoluteValue extends UnaryOperation {

    public AbsoluteValue(Function function) {
        super(function, (a) -> (float) Math.abs(a), "|");
    }

    @Override
    public String asString(){
        return symbol + function.asString() + symbol;
    }
}