package com.example.myapplication.Expressions;

public final class Cotangent extends UnaryOperation {
    public Cotangent(Function function) {
        super(function, (a) -> (float) ((float)     1.0/ Math.tan(a)), "ctg");
    }

    @Override
    public String asString(){
        return symbol+"(" + function.asString()+")";
    }

}
