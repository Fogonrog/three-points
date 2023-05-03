package com.example.myapplication;


import static com.example.myapplication.Expressions.Functions.div;
import static com.example.myapplication.Expressions.Functions.mul;
import static com.example.myapplication.Expressions.Functions.n;
import static com.example.myapplication.Expressions.Functions.pow;
import static com.example.myapplication.Expressions.Functions.sin;
import static com.example.myapplication.Expressions.Functions.sub;
import static com.example.myapplication.Expressions.Functions.sum;
import static com.example.myapplication.Expressions.Functions.x;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.myapplication.Expressions.Argument;
import com.example.myapplication.Expressions.Function;

public class MyDialogFragment extends DialogFragment {
    public Function function;
    private TextView textFunction;
    private Button btnC;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnOk;
    private Button btnNoOk;
    private View miniCanvas;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_custom, null);
        builder.setView(view);

        textFunction = view.findViewById(R.id.x);

        Draw2D.functionOnTheCanvas = true;

        miniCanvas = view.findViewById(R.id.minicanvas);

        btnOk = view.findViewById(R.id.button_ok);
        btnOk.setOnClickListener(v -> {
            Draw2D.function = this.function;
            InputFragment.canvas.invalidate();
            builder.dismiss();
        });

        btnNoOk = view.findViewById(R.id.button_nook);
        btnNoOk.setOnClickListener(v -> {
            Draw2D.function = x;
            miniCanvas.invalidate();
            builder.dismiss();
        });


        btnC = view.findViewById(R.id.button1);
        btnC.setText("C");
        btnC.setOnClickListener(v -> setFunction(x));

        btn2 = view.findViewById(R.id.button2);
        btn2.setText("..²");
        btn2.setOnClickListener(v -> setFunction(pow(function,n(2))));

        btn3 = view.findViewById(R.id.button3);
        btn3.setText("..³");
        btn3.setOnClickListener(v -> setFunction(pow(function,n(3))));

        btn4 = view.findViewById(R.id.button4);
        btn4.setText("+2");
        btn4.setOnClickListener(v -> setFunction(sum(function,n(2))));

        btn5 = view.findViewById(R.id.button5);
        btn5.setText("-7");
        btn5.setOnClickListener(v -> setFunction(sub(function,n(7))));

        btn6 = view.findViewById(R.id.button6);
        btn6.setText("/2");
        btn6.setOnClickListener(v -> setFunction(div(function,n(2))));

        btn7 = view.findViewById(R.id.button7);
        btn7.setText("-2х");
        btn7.setOnClickListener(v -> setFunction(sub(function,mul(x,n(2)))));

        btn8 = view.findViewById(R.id.button8);
        btn8.setText("+5х");
        btn8.setOnClickListener(v -> setFunction(sum(function,mul(x,n(5)))));

        btn9= view.findViewById(R.id.button9);
        btn9.setText("×7");
        btn9.setOnClickListener(v -> setFunction(mul(function,n(7))));

        function = x;
        Draw2D.function = this.function;
        updateTextView();
        miniCanvas.invalidate();
        return builder;
    }

    private void setFunction(Function function) {
        this.function = function;
        Draw2D.function = this.function;
        miniCanvas.invalidate();
        System.out.println(function.asString());
        updateTextView();
    }

    private void updateTextView() {
        textFunction.setText(function.asString());
    }
}
