package com.example.myapplication.expressions;

public abstract class BinaryOperation implements Function {

    private final Function left;
    private final Function right;
    private final String symbol;
    private final Combine combine;

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

    protected final Function left() {
        return left;
    }

    protected final Function right() {
        return right;
    }

    protected final String symbol() {
        return symbol;
    }

    @Override
    public final float evaluate(float x) {
        return combine.apply(
                left.evaluate(x),
                right.evaluate(x)
        );
    }
    @Override
    public abstract String asString();

    @FunctionalInterface
    public interface Combine {
        float apply(float left, float right);
    }
}
