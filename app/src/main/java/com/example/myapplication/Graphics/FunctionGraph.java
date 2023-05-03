package com.example.myapplication.Graphics;

import com.example.myapplication.Expressions.Function;

public class FunctionGraph implements Drawable {
    private final Function function;

    public FunctionGraph(Function function) {
        this.function = function;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.paint.setStrokeWidth(8);
        for (float i = 0F; i < 500; i += 0.01F) {
            canvas.origin.drawLine(i, (float) (function.evaluate(i)),i + 0.01F, (float) (function.evaluate(i+ 0.01F)),canvas.paint);
            canvas.origin.drawLine(-i, (float) (function.evaluate(-i)),-i - 0.01F, (float) (function.evaluate(-i - 0.01F)),canvas.paint);
        }
        canvas.paint.setStrokeWidth(1);
    }
}
