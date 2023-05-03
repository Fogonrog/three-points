package com.example.myapplication;

import static com.example.myapplication.Expressions.Functions.x;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.Expressions.Function;
import com.example.myapplication.Graphics.Colored;
import com.example.myapplication.Graphics.Container;
import com.example.myapplication.Graphics.Drawable;
import com.example.myapplication.Graphics.FunctionGraph;
import com.example.myapplication.Graphics.Line;
import com.example.myapplication.Graphics.Point;

import java.util.List;

public class Draw2D extends View {
    public static boolean functionOnTheCanvas = false;
    public static Function function = x;
    private Paint paint = new Paint();
    private Canvas canvas;

    public Draw2D(Context context, Function func, boolean flag) {
        super(context);
        this.function = func;
        this.functionOnTheCanvas = flag;
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        float width = (float)getWidth();
        float height = (float)getHeight();

        moveStartingPoint();
        initialСanvasPreparation();
        com.example.myapplication.Graphics.Canvas mainCanvas = new com.example.myapplication.Graphics.Canvas(canvas, paint);

        Drawable axes = new Container(List.of(
                new Line(new Point(0F, (height/2)), new Point(0F, (height/-2))),
                new Line(new Point((width/2), 0F), new Point((width/-2), 0F)),
                new Line(new Point(20F,(height/2-20)), new Point(0F,(height/2))),
                new Line(new Point(-20F,(height/2-20)), new Point(0F,(height/2))),
                new Line(new Point((width/2-20),-20F), new Point((width/2),0F)),
                new Line(new Point((width/2-20),20F), new Point((width/2),0F))
        ));
        Drawable func = new FunctionGraph(function);
        Drawable redFunc = new Colored(Color.RED,func);
        Drawable blackAxes = new Colored(Color.BLACK, axes);
        mainCanvas.draw(blackAxes);

        if (functionOnTheCanvas) {
            mainCanvas.draw(redFunc);
        }
    }

    private void initialСanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint() {
        canvas.translate(getWidth()/ 2, getHeight()/ 2);
        canvas.scale(1f, -1f);
    }
}