package com.example.myapplication.view;

import static com.example.myapplication.logic.expression.Functions.x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.logic.model.Level;
import com.example.myapplication.R;
import com.example.myapplication.logic.expression.Function;
import com.example.myapplication.logic.graphics.Canva;
import com.example.myapplication.logic.graphics.Colored;
import com.example.myapplication.logic.graphics.Container;
import com.example.myapplication.logic.graphics.Drawable;
import com.example.myapplication.logic.graphics.Filled;
import com.example.myapplication.logic.graphics.FunctionGraph;
import com.example.myapplication.logic.graphics.Line;
import com.example.myapplication.logic.graphics.Point;
import com.example.myapplication.logic.graphics.Scaled;

import java.util.List;

public final class Draw2D extends View {

    private static final float A = 7.0f;
    private static final float INDENT = 20F;
    private static final float NUM_FOUR = 4F;
    private static final int LARGE_WIDTH = 8;
    private final boolean isBigCanvas;
    private final Paint paint = new Paint();
    private float widthMlt;
    private Function function = x();
    private Drawable axes;
    private FunctionGraph func;
    private Level level;

    public Draw2D(Context context) {
        this(context, null);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isBigCanvas = getId() == R.id.canvas;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
        invalidate();
    }

    public float getWidthMlt() {
        return this.widthMlt;
    }

    public boolean isRightFunction() {
        var result = true;
        for (var reqObstacle : level.getStage().getCheckpoints()) {
            result = result && reqObstacle.intersects(func);
        }
        for (var forbObstacle : level.getStage().getObstacles()) {
            result = result && !(forbObstacle.intersects(func));
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (axes == null) {
            lazyInit();
        }
        moveStartingPoint(canvas);
        initialCanvasPreparation(canvas);
        var mainCanvas = Canva.from(canvas, paint);
        var func = FunctionGraph.from(function, widthMlt);
        this.func = func;

        mainCanvas.draw(axes);
        if (isBigCanvas) {
            for (Drawable background : level.getStage().getBackground()) {
                mainCanvas.draw(background);
            }
            for (Drawable reqObstacle : level.getStage().getCheckpoints()) {
                mainCanvas.draw(reqObstacle);
            }
            for (Drawable forbObstacle : level.getStage().getObstacles()) {
                mainCanvas.draw(forbObstacle);
            }
        }
        mainCanvas.draw(Colored.from("#FFFF0000",
                Scaled.from(LARGE_WIDTH,
                        Filled.from(false, func))));
    }

    private void initialCanvasPreparation(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void lazyInit() {
        float width = getWidth();
        float height = getHeight();
        axes = Colored.from("#FF000000", Scaled.from(1, Container.from(List.of(
                Line.of(Point.of(0F, height / 2),
                        Point.of(0F, -height / 2)),
                Line.of(Point.of(width / 2, 0F),
                        Point.of(-width / 2, 0F)),
                Line.of(Point.of(INDENT, (height / 2 - INDENT)),
                        Point.of(0F, height / 2)),
                Line.of(Point.of(-INDENT, (height / 2 - INDENT)),
                        Point.of(0F, height / 2)),
                Line.of(Point.of((width / 2 - INDENT), -INDENT),
                        Point.of(width / 2, 0F)),
                Line.of(Point.of((width / 2 - INDENT), INDENT),
                        Point.of(width / 2, 0F))
        ))));
        var l = -width / NUM_FOUR - NUM_FOUR;
        this.widthMlt = -l / A;
    }

    private void moveStartingPoint(Canvas canvas) {
        canvas.translate((float) getWidth() / 2, (float) getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}
