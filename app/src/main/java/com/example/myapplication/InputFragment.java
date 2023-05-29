package com.example.myapplication;

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
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class InputFragment extends DialogFragment {

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

        List<Function> functions;
        try {
            var objectMapper = new ObjectMapper();
            var stream = readFileInAssets("person.json");
            functions = objectMapper.readValue(stream, Funci.class).getFunctions();
            System.out.println(functions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        btn2.setText(functions.get(0).getStrSingleFunction());
        btn2.setOnClickListener(v -> {
            Function firstFunc;
            try {
                firstFunc = functions.get(0).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            firstFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(firstFunc);
        });

        Button btn3 = view.findViewById(R.id.button3);
        btn3.setText(functions.get(1).getStrSingleFunction());
        btn3.setOnClickListener(v -> {
            Function secondFunc;
            try {
                secondFunc = functions.get(1).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            secondFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(secondFunc);
        });

        Button btn4 = view.findViewById(R.id.button4);
        btn4.setText(functions.get(2).getStrSingleFunction());
        btn4.setOnClickListener(v -> {
            Function thirdFunc;
            try {
                thirdFunc = functions.get(2).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            thirdFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(thirdFunc);
        });

        Button btn5 = view.findViewById(R.id.button5);
        btn5.setText(functions.get(3).getStrSingleFunction());
        btn5.setOnClickListener(v -> {
            Function fourthFunc;
            try {
                fourthFunc = functions.get(3).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            fourthFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(fourthFunc);
        });

        Button btn6 = view.findViewById(R.id.button6);
        btn6.setText(functions.get(4).getStrSingleFunction());
        btn6.setOnClickListener(v -> {
            Function fifthFunc;
            try {
                fifthFunc = functions.get(4).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            fifthFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(fifthFunc);
        });

        Button btn7 = view.findViewById(R.id.button7);
        btn7.setText(functions.get(5).getStrSingleFunction());
        btn7.setOnClickListener(v -> {
            Function sixthFunc;
            try {
                sixthFunc = functions.get(5).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            sixthFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(sixthFunc);
        });

        Button btn8 = view.findViewById(R.id.button8);
        btn8.setText(functions.get(6).getStrSingleFunction());
        btn8.setOnClickListener(v -> {
            Function seventhFunc;
            try {
                seventhFunc = functions.get(6).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            seventhFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(seventhFunc);
        });

        Button btn9 = view.findViewById(R.id.button9);
        btn9.setText(functions.get(7).getStrSingleFunction());
        btn9.setOnClickListener(v -> {
            Function eightFunc;
            try {
                eightFunc = functions.get(7).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            eightFunc.setCurrentFunction(miniCanvas.getFunction());
            setFunction(eightFunc);
        });

        updateTextView();
        return builder;
    }
    public String readFileInAssets(String name) {

        byte[] buffer = null;
        InputStream is;
        try {
            is = this.getActivity().getAssets().open(name);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
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
