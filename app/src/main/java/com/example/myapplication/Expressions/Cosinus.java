package com.example.myapplication.Expressions;

public final class Cosinus extends UnaryOperation {

    public Cosinus(Function function) {
        super(function, (a) -> (float) Math.cos(a), "cos");
    }

    @Override
    public String asString(){
        return symbol+"(" + function.asString()+")";
    }
}
