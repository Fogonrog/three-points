package com.example.myapplication.Graphics;

import com.example.myapplication.Expressions.Function;

public class FunctionGraph implements Drawable {
    private final Function function;
    private final Integer color;

    public FunctionGraph(Function function,Integer color) {
        this.function = function;
        this.color = color;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.paint.setColor(color);
        for (float i = 0F; i < 30; i += 0.001F) {
            canvas.origin.drawCircle(i * 10, (float) (function.evaluate(i) * 10), 2, canvas.paint);
            canvas.origin.drawCircle(-i * 10, (float) (function.evaluate(-i) * 10), 2, canvas.paint);
        }
    }
}
