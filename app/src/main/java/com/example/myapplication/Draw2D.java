package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Draw2D extends View {

    private Paint mPaint = new Paint();
    public Bitmap mBitmap;
    public final int WIDTH;
    public final int HEIGHT;
    public Canvas canvas;




    public Draw2D(Context context,int width,int height) {
        super(context);
        this.WIDTH = width;
        this.HEIGHT = height;
        // Выводим значок из ресурсов
        Resources res = this.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.first);
    }

    @Override
    protected void onDraw(Canvas canva) {
        super.onDraw(canvas);
        this.canvas = canva;
        this.canvas.translate(WIDTH/2,HEIGHT/2);
        this.canvas.rotate(180);

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);

        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        // Рисуем жёлтый круг
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        //Рисуем фон
        Rect srcRect = new Rect( 0, 0,mBitmap.getWidth()/2, mBitmap.getHeight()/2);
        Rect dstRect = new Rect(0, 0,WIDTH/2, HEIGHT/2);
        canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
        
        mPaint.setColor(Color.RED);
        canvas.drawCircle(-20,-20, 10, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(20,20, 10, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(-20,20, 10, mPaint);

        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(20,-20, 10, mPaint);

        //Рисуем параболу
        mPaint.setColor(Color.RED);
        for (float i = 0F; i < 25; i+=0.01F) {
            canvas.drawCircle(i*10, (float) (i*i), 3, mPaint);
            canvas.drawCircle(-i*10, (float) (i*i), 3, mPaint);
        }
    }
}