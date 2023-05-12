package com.example.myapplication.graphics;

import android.graphics.Path;

import com.example.myapplication.expressions.Function;

public final class FunctionGraph implements Drawable {
    private static final float MAX_VALUE_X = 500F;
    private static final float STEP_X = 1F;
    private final Function function;
    private final Path path;

    public FunctionGraph(Function function) {
        this.function = function;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    public static FunctionGraph from(Function function) {
        return new FunctionGraph(function);
    }
    @Override
    public void drawOn(Canva canvas) {
        path.moveTo(-MAX_VALUE_X, (float) (function.evaluate(-MAX_VALUE_X)));
        for (float i = -MAX_VALUE_X; i < 0; i += STEP_X) {
            path.lineTo(i, (float) (function.evaluate(i)));
        }
        for (float i = 0F; i < MAX_VALUE_X; i += STEP_X) {
            path.lineTo(i, (float) (function.evaluate(i)));
        }
        canvas.origin.drawPath(path,canvas.paint);
    }
}
