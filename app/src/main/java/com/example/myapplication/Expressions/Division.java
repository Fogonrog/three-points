package com.example.myapplication.Expressions;

public final class Division extends BinaryOperation {

    public Division(Function left, Function right) {
        super(left, right, (a, b) -> a / b, "/");
    }

    @Override
    public String asString(){
        return left.asString() + " " + symbol + " " + right.asString() ;
    }
}
