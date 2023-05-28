package com.example.myapplication;

import static com.example.myapplication.expressions.Functions.div;
import static com.example.myapplication.expressions.Functions.mul;
import static com.example.myapplication.expressions.Functions.n;
import static com.example.myapplication.expressions.Functions.pow;
import static com.example.myapplication.expressions.Functions.sub;
import static com.example.myapplication.expressions.Functions.sum;
import static com.example.myapplication.expressions.Functions.x;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.expressions.Power;
import com.example.myapplication.graphics.Colored;
import com.example.myapplication.graphics.Container;
import com.example.myapplication.graphics.Line;
import com.example.myapplication.graphics.Point;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public String readFileInAssets(String name){

        String text = name;
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(text);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str_data = new String(buffer);
        return str_data;
    }
}
