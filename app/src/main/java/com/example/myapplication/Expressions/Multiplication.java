package com.example.myapplication.Expressions;

public final class Multiplication extends BinaryOperation {
    @Override
    public String asString(){
        return left.asString() + " " + symbol + " " + right.asString() ;
    }
    public Multiplication(Function left, Function right) {
        super(left, right, (a, b) -> a * b, "×");
    }
}
