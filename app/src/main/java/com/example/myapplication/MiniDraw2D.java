package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MiniDraw2D extends View {
    public MiniDraw2D(Context context) {
        super(context);
    }

    public MiniDraw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(600, 600);
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);

        paint.setColor(Color.RED);
        canvas.drawCircle(35,35,35,paint);
    }
}
