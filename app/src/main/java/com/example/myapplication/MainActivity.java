package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;

import com.example.myapplication.Expressions.Expr;
import com.example.myapplication.Expressions.Multi;
import com.example.myapplication.Expressions.NodeX;
import com.example.myapplication.Expressions.Num;
import com.example.myapplication.Expressions.Square;
import com.example.myapplication.Expressions.Sub;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Expr func = new NodeX();
        func = new Sub(func,new Num(2));
        func = new Square(func);
        func = new Multi(func, new Num(-1));
        for (float i = 0; i < 10; i+=0.5) {
            float y = new Expr(func).eval(i);
            System.out.println("x->"+i);
            System.out.println("y->"+y);
            System.out.println();
        }


        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated

        Draw2D draw2D = new Draw2D(this,width,height);
        setContentView(draw2D);
    }
}