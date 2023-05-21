package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public final class MainFragment extends Fragment {

    private Draw2D canvas;

    public View getCanvas() {
        return canvas;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        var view = inflater.inflate(R.layout.fragment_input, container, false);
        canvas = view.findViewById(R.id.canvas);

        var btnAddFunction = view.findViewById(R.id.add_function);
        btnAddFunction.setOnClickListener(v -> {
            var manager = MainFragment.this.getParentFragmentManager();
            var inputFragment = new InputFragment(canvas);
            inputFragment.show(manager, "myDialog");
        });

        var btnRun = view.findViewById(R.id.runbutton);
        btnRun.setOnClickListener(v -> {
            var manager = MainFragment.this.getParentFragmentManager();
            var runFragment = new RunFragment(canvas);
            runFragment.show(manager, "myDialog");
        });

        return view;
    }
}
