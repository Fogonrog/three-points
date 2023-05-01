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
    private Bitmap bitmap;
    private Canvas canvas;

    public Draw2D(Context context) {
        super(context);
        Resources res = this.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.first);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Resources res = this.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.first);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        moveStartingPoint();
        initialСanvasPreparation();
        com.example.myapplication.Graphics.Canvas mainCanvas = new com.example.myapplication.Graphics.Canvas(canvas, paint);

        Drawable axes = new Container(List.of(
                new Line(new Point(0F, (float)getHeight()/2), new Point(0F, (float)getHeight()/-2), Color.BLACK),
                new Line(new Point((float)getWidth()/2, 0F), new Point((float)getWidth()/-2, 0F), Color.BLACK),
                new Line(new Point(20F,(float)getHeight()/2-20), new Point(0F,(float)getHeight()/2), Color.BLACK),
                new Line(new Point(-20F,(float)getHeight()/2-20), new Point(0F,(float)getHeight()/2), Color.BLACK),
                new Line(new Point((float)(getWidth()/2)-20,-20F), new Point((float)(getWidth()/2),0F), Color.BLACK),
                new Line(new Point((float)(getWidth()/2)-20,20F), new Point((float)(getWidth()/2),0F), Color.BLACK)
        ));
        mainCanvas.draw(axes);

        Drawable func = new FunctionGraph(function,Color.RED);

        if (functionOnTheCanvas) {
            mainCanvas.draw(func);
        }
    }

    private void initialСanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint() {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}