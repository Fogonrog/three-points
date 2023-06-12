package com.example.myapplication.view.fragments;

import static com.example.myapplication.logic.expressions.Functions.x;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.logic.model.Level;
import com.example.myapplication.serialization.StageJSON;
import com.example.myapplication.R;
import com.example.myapplication.logic.expressions.Function;
import com.example.myapplication.view.Draw2D;

import java.util.List;

public final class InputFragment extends DialogFragment {

    private final Draw2D bigCanvas;
    private final Level level;
    private TextView textFunction;
    private Draw2D miniCanvas;

    public InputFragment(Draw2D bigCanvas, Level level) {
        super();
        this.bigCanvas = bigCanvas;
        this.level = level;
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
        miniCanvas.setLevel(level);

        var functions = level.getStage().getFunctions();

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
        Button btn3 = view.findViewById(R.id.button3);
        Button btn4 = view.findViewById(R.id.button4);
        Button btn5 = view.findViewById(R.id.button5);
        Button btn6 = view.findViewById(R.id.button6);
        Button btn7 = view.findViewById(R.id.button7);
        Button btn8 = view.findViewById(R.id.button8);
        Button btn9 = view.findViewById(R.id.button9);

        List<Button> buttons = List.of(btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9);

        int i = 0;
        for (Button btn : buttons) {
            btn.setText(functions.get(i).getStrSingleFunction());
            int finalI = i;
            btn.setOnClickListener(v -> {
                Function func;
                try {
                    func = functions.get(finalI).clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
                func.setCurrentFunction(miniCanvas.getFunction());
                setFunction(func);
            });
            i++;
        }

        updateTextView();
        return builder;
    }

    private void setFunction(Function function) {
        miniCanvas.setFunction(function);
        System.out.println(miniCanvas.getFunction().asString());
        updateTextView();
    }

    @SuppressLint("SetTextI18n")
    private void updateTextView() {
        textFunction.setText("y = " + miniCanvas.getFunction().asString());
    }

}
