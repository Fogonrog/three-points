package com.example.myapplication.Expressions;

public final class Division extends BinaryOperation {
    @Override
    public String asString(){
        return left.asString() + " " + symbol + " " + right.asString() ;
    }
    public Division(Function left, Function right) {
        super(left, right, (a, b) -> a / b, "/");
    }
}
