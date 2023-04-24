package com.example.myapplication.Expressions;

public final class Power extends BinaryOperation {
    @Override
    public String asString(){
        return "(" +left.asString() + ")"+ symbol + right.asString() ;
    }
    public Power(Function left, Function right) {
        super(left, right, (a, b) -> (float) Math.pow(a,b), "^");
    }
}