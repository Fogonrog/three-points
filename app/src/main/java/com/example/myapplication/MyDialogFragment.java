package com.example.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.myapplication.Expressions.Argument;
import com.example.myapplication.Expressions.Division;
import com.example.myapplication.Expressions.Function;
import com.example.myapplication.Expressions.Multiplication;
import com.example.myapplication.Expressions.Num;
import com.example.myapplication.Expressions.Power;
import com.example.myapplication.Expressions.Sub;
import com.example.myapplication.Expressions.Sum;

public class MyDialogFragment extends DialogFragment {
    public Function function;
    private TextView TextFunction;
    private Button Btn_с;
    private Button Btn_2;
    private Button Btn_3;
    private Button Btn_4;
    private Button Btn_5;
    private Button Btn_6;
    private Button Btn_7;
    private Button Btn_8;
    private Button Btn_9;
    private Button Btn_ok;
    private Button Btn_no_ok;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_custom, null);
        builder.setView(view);
        // Остальной код
        TextFunction = view.findViewById(R.id.x);

        Btn_ok = view.findViewById(R.id.button_ok);
        Btn_ok.setOnClickListener(v -> builder.cancel());

        Btn_no_ok = view.findViewById(R.id.button_nook);
        Btn_no_ok.setOnClickListener(v -> builder.cancel());

        Btn_с = view.findViewById(R.id.button1);
        Btn_с.setText("C");

        Btn_2 = view.findViewById(R.id.button2);
        Btn_2.setText("..²");

        Btn_3 = view.findViewById(R.id.button3);
        Btn_3.setText("..³");

        Btn_4 = view.findViewById(R.id.button4);
        Btn_4.setText("+2");

        Btn_5 = view.findViewById(R.id.button5);
        Btn_5.setText("-7");

        Btn_6 = view.findViewById(R.id.button6);
        Btn_6.setText("÷2");

        Btn_7 = view.findViewById(R.id.button7);
        Btn_7.setText("-2х");

        Btn_8 = view.findViewById(R.id.button8);
        Btn_8.setText("+5х");

        Btn_9= view.findViewById(R.id.button9);
        Btn_9.setText("×7");

        Button[] list_of_button = {Btn_с,Btn_2,Btn_3,Btn_4,Btn_5,Btn_6,Btn_7,Btn_8,Btn_9};
        for (Button btn : list_of_button) {
            btn.setOnClickListener(v -> setFunction(v));
        }
        function = new Argument();
        updateTextView();

        return builder;
    }

    private void updateTextView() {
        TextFunction.setText(function.asString());
    }

    public void setFunction(View view) {
        switch (view.getId()) {
            case R.id.button1:
                function = new Argument();
                updateTextView();
                break;
            case R.id.button2:
                function = new Power(function,new Num(2));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button3:
                function = new Power(function,new Num(3));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button4:
                function = new Sum(function,new Num(2));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button5:
                function = new Sub(function,new Num(7));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button6:
                function = new Division(function,new Num(2));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button7:
                function = new Sub(function,new Multiplication(new Argument(),new Num(2)));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button8:
                function = new Sum(function,new Multiplication(new Argument(),new Num(5)));
                System.out.println(function.asString());
                updateTextView();
                break;
            case R.id.button9:
                function = new Multiplication(function,new Num(7));
                System.out.println(function.asString());
                updateTextView();
                break;
        }
    }
}
