package com.example.myapplication.Expressions;

public final class Sinus extends UnaryOperation {

    public Sinus(Function function) {
        super(function, (a) -> (float) Math.sin(a), "sin");
    }

    @Override
    public String asString(){
        return symbol+"(" + function.asString()+")";
    }
}
