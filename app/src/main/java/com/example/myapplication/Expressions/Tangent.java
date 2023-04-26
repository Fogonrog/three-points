package com.example.myapplication.Expressions;

public final class Tangent extends UnaryOperation {

    public Tangent(Function function) {
        super(function, (a) -> (float) Math.tan(a), "tg");
    }

    @Override
    public String asString(){
        return symbol+"(" + function.asString()+")";
    }
}
