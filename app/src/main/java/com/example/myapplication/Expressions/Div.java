package com.example.myapplication.Expressions;

public class Div extends Expr {
    public Expr function1;
    public Expr function2;

    public Div(Expr func1, Expr func2) {
        this.function1 = func1;
        this.function2 = func2;
    }
    @Override
    public float eval(float x) {
        return function1.eval(x) / function2.eval(x);
    }
}