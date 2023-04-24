package com.example.myapplication.Expressions;

public abstract class BinaryOperation implements Function {

    @FunctionalInterface
    public interface Combine {
        float apply(float left, float right);
    }

    protected final Function left;
    protected final Function right;

    protected final Combine combine;
    protected final String symbol;

    public BinaryOperation(
            Function left,
            Function right,
            Combine combine,
            String symbol
    ) {
        this.left = left;
        this.right = right;
        this.combine = combine;
        this.symbol = symbol;
    }

    @Override
    public float evaluate(float x) {
        return combine.apply(
                left.evaluate(x),
                right.evaluate(x)
        );
    }

    @Override
    public String asString() {
        return "(" + left.asString() + " " + symbol + " " + right.asString() + ")";
    }
}