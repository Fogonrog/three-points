package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.myapplication.R;

public class InputFragment extends Fragment{
    public static View canvas;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        ImageButton button_add_function = view.findViewById(R.id.add_function);
        canvas = view.findViewById(R.id.canvas);
        button_add_function.setOnClickListener(v -> {
            FragmentManager manager = InputFragment.this.getParentFragmentManager();
            MyDialogFragment myDialogFragment = new MyDialogFragment();
            myDialogFragment.show(manager, "myDialog");
        });
        return view;
    }
}
