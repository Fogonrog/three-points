package com.example.myapplication.expressions;

import net.objecthunter.exp4j.ExpressionBuilder;

public final class Functions {
    private Functions() { }
    public static Argument x() {
        return new Argument();
    }

    public static Num n(float value) {
        return new Num(value);
    }

    public static Multiplication mul(Function left, Function right) {
        return new Multiplication(left, right);
    }

    public static AbsoluteValue abs(Function function) {
        return new AbsoluteValue(function);
    }

    public static Exp exp(Function function) {
        return new Exp(function);
    }

    public static Cosine cos(Function function) {
        return new Cosine(function);
    }

    public static Cotangent ctg(Function function) {
        return new Cotangent(function);
    }

    public static Division div(Function left, Function right) {
        return new Division(left, right);
    }

    public static Power pow(Function left, Function right) {
        return new Power(left, right);
    }

    public static Sinus sin(Function function) {
        return new Sinus(function);
    }

    public static Sqrt sqrt(Function function) {
        return new Sqrt(function);
    }

    public static Sub sub(Function left, Function right) {
        return new Sub(left, right);
    }

    public static Sum sum(Function left, Function right) {
        return new Sum(left, right);
    }

    public static Tangent tg(Function function) {
        return new Tangent(function);
    }
    public static float expr(String string) {
        return (float) new ExpressionBuilder(string)
                .build()
                .evaluate();
    }
}
