package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import com.example.myapplication.Expressions.Function;
import com.example.myapplication.Expressions.Multiplication;
import com.example.myapplication.Expressions.Argument;
import com.example.myapplication.Expressions.Num;
import com.example.myapplication.Expressions.Power;
import com.example.myapplication.Expressions.Sub;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Function func = new Argument();
        func = new Sub(func,new Num(2));
        func = new Power(func,2);
        func = new Multiplication(func, new Num(-1));
        for (float i = 0; i < 10; i += 0.5) {
            float y = func.evaluate(i);
            System.out.println("x->" + i);
            System.out.println("y->" + y);
            System.out.println();
        }
        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);
    }
}