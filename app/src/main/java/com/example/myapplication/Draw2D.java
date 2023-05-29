package com.example.myapplication;

import static com.example.myapplication.expressions.Functions.x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.Canva;
import com.example.myapplication.graphics.Colored;
import com.example.myapplication.graphics.Container;
import com.example.myapplication.graphics.Figure;
import com.example.myapplication.graphics.FunctionGraph;
import com.example.myapplication.graphics.Line;
import com.example.myapplication.graphics.Point;
import com.example.myapplication.graphics.Polygon;
import com.example.myapplication.graphics.Scaled;
import java.util.ArrayList;
import java.util.List;

public final class Draw2D extends View {

    private static final float A = 7.0f;
    private static final float INDENT = 20F;
    private static final float NUM_THREE = 3F;
    private static final float NUM_FOUR = 4F;
    private static final int LARGE_WIDTH = 8;
    private final boolean isBigCanvas;
    private final Paint paint = new Paint();
    private final List<Figure> obstacles = new ArrayList<>();
    private float widthMlt;
    private Function function = x();
    private Container axes;
    private Polygon leftCoast;
    private Polygon rightCoast;
    private FunctionGraph func;
    private Canvas canvas;

    public Draw2D(Context context) {
        this(context, null);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.isBigCanvas = getId() == R.id.canvas;
    }

    public Function getFunction() {
        return this.function;
    }

    public void setFunction(Function function) {
        this.function = function;
        invalidate();
    }

    public boolean isRightFunction() {
        var result = true;
        for (var obstacle : obstacles) {
            result = result && obstacle.intersects(func);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        if (axes == null) {
            lazyInit();
        }
        moveStartingPoint(canvas);
        initialCanvasPreparation(canvas);
        var mainCanvas = Canva.from(canvas, paint);
        var func = FunctionGraph.from(function, widthMlt);
        this.func = func;

        mainCanvas.draw(Colored.from(Color.BLACK, Scaled.from(1, axes)));
        if (isBigCanvas) {
            mainCanvas.draw(Colored.from(Color.BLACK, Scaled.from(LARGE_WIDTH, leftCoast)));
            mainCanvas.draw(Colored.from(Color.BLACK, Scaled.from(LARGE_WIDTH, rightCoast)));
        }
        mainCanvas.draw(Colored.from(Color.RED, Scaled.from(LARGE_WIDTH, func)));
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
        axes = Container.from(List.of(
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
        ));
        leftCoast = Polygon.from(List.of(
                Point.of(-width / 2, -INDENT),
                Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                Point.of(-width / NUM_THREE, -height / NUM_THREE),
                Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                Point.of(-width / NUM_THREE, -height / NUM_THREE),
                Point.of(-width / NUM_FOUR - 2 * INDENT, -height / 2),
                Point.of(-width / 2, -height / 2),
                Point.of(-width / 2, -INDENT)));
        rightCoast = Polygon.from(List.of(
                Point.of(width / 2, -INDENT),
                Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                Point.of(width / NUM_THREE, -height / NUM_THREE),
                Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                Point.of(width / NUM_THREE, -height / NUM_THREE),
                Point.of(width / NUM_FOUR + 2 * INDENT, -height / 2),
                Point.of(width / 2, -height / 2),
                Point.of(width / 2, -INDENT)));
        var leftLine = Line.of(Point.of(-width / 2, -INDENT),
                Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT));
        var rightLine = Line.of(Point.of(width / 2, -INDENT),
                Point.of(width / NUM_FOUR - NUM_FOUR, -INDENT));
        obstacles.add(leftLine);
        obstacles.add(rightLine);

        // посчитаем масштаб
        var l = -width / NUM_FOUR - NUM_FOUR;
        // пусть мы считаем, что в точке l должнa быть координаты x = -a
        // тогда масштаб
        widthMlt = -l / A;
    }

    private void moveStartingPoint(Canvas canvas) {
        canvas.translate((float) getWidth() / 2, (float) getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}
