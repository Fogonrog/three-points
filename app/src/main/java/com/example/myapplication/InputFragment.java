package com.example.myapplication;


import static com.example.myapplication.expressions.Functions.div;
import static com.example.myapplication.expressions.Functions.mul;
import static com.example.myapplication.expressions.Functions.n;
import static com.example.myapplication.expressions.Functions.pow;
import static com.example.myapplication.expressions.Functions.sub;
import static com.example.myapplication.expressions.Functions.sum;
import static com.example.myapplication.expressions.Functions.x;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import com.example.myapplication.expressions.Function;

public final class InputFragment extends DialogFragment {

    private static final float NUM_TWO = 2F;
    private static final float NUM_THREE = 3F;
    private static final float NUM_SEVEN = 7F;
    private static final float NUM_FIVE = 5F;

    private TextView textFunction;
    private Draw2D miniCanvas;

    private final Draw2D bigCanvas;

    public InputFragment(Draw2D bigCanvas) {
        super();
        this.bigCanvas = bigCanvas;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_custom, null);
        builder.setView(view);

        textFunction = view.findViewById(R.id.x);
        miniCanvas = view.findViewById(R.id.minicanvas);
        miniCanvas.setFunction(x());

        Button btnOk = view.findViewById(R.id.button_ok);
        btnOk.setOnClickListener(v -> {
            bigCanvas.setFunction(miniCanvas.getFunction());
            builder.dismiss();
        });

        Button btnNoOk = view.findViewById(R.id.button_nook);
        btnNoOk.setOnClickListener(v -> builder.dismiss());

        Button btnC = view.findViewById(R.id.button1);
        btnC.setText("C");
        btnC.setOnClickListener(v -> setFunction(x()));

        Button btn2 = view.findViewById(R.id.button2);
        btn2.setText("..²");
        btn2.setOnClickListener(v -> setFunction(pow(miniCanvas.getFunction(), n(NUM_TWO))));

        Button btn3 = view.findViewById(R.id.button3);
        btn3.setText("..³");
        btn3.setOnClickListener(v -> setFunction(pow(miniCanvas.getFunction(), n(NUM_THREE))));

        Button btn4 = view.findViewById(R.id.button4);
        btn4.setText("+2");
        btn4.setOnClickListener(v -> setFunction(sum(miniCanvas.getFunction(), n(NUM_TWO))));

        Button btn5 = view.findViewById(R.id.button5);
        btn5.setText("-7");
        btn5.setOnClickListener(v -> setFunction(sub(miniCanvas.getFunction(), n(NUM_SEVEN))));

        Button btn6 = view.findViewById(R.id.button6);
        btn6.setText("/2");
        btn6.setOnClickListener(v -> setFunction(div(miniCanvas.getFunction(), n(NUM_TWO))));

        Button btn7 = view.findViewById(R.id.button7);
        btn7.setText("-2х");
        btn7.setOnClickListener(v -> setFunction(
                sub(miniCanvas.getFunction(), mul(x(), n(NUM_TWO)))));

        Button btn8 = view.findViewById(R.id.button8);
        btn8.setText("+5х");
        btn8.setOnClickListener(v -> setFunction(
                sum(miniCanvas.getFunction(), mul(x(), n(NUM_FIVE)))));

        Button btn9 = view.findViewById(R.id.button9);
        btn9.setText("×7");
        btn9.setOnClickListener(v -> setFunction(mul(miniCanvas.getFunction(), n(NUM_SEVEN))));

        updateTextView();
        return builder;
    }

    private void setFunction(Function function) {
        miniCanvas.setFunction(function);
        System.out.println(miniCanvas.getFunction().asString());
        updateTextView();
    }

    private void updateTextView() {
        textFunction.setText("y = " + miniCanvas.getFunction().asString());
    }

}
