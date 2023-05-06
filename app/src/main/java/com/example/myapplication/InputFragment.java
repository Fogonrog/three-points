package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public final class InputFragment extends Fragment {
    private static View canvas;

    public static View getCanvas() {
        return canvas;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        var view = inflater.inflate(R.layout.fragment_input, container, false);
        var buttonAddFunction = view.findViewById(R.id.add_function);
        canvas = view.findViewById(R.id.canvas);
        buttonAddFunction.setOnClickListener(v -> {
            var manager = InputFragment.this.getParentFragmentManager();
            var myDialogFragment = new MyDialogFragment();
            myDialogFragment.show(manager, "myDialog");
        });
        return view;
    }
}
