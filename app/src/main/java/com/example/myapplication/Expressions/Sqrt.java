package com.example.myapplication.Expressions;

public final class Sqrt extends UnaryOperation {
    @Override
    public String asString(){
        return symbol+"(" + function.asString()+")";
    }
    public Sqrt(Function function) {
        super(function, (a) -> (float) Math.sqrt(a), "√");
    }
}
