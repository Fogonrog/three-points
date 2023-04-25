package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.Expressions.Function;
import com.example.myapplication.Expressions.Multiplication;
import com.example.myapplication.Expressions.Argument;
import com.example.myapplication.Expressions.Num;
import com.example.myapplication.Expressions.Power;
import com.example.myapplication.Expressions.Sub;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}