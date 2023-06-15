package com.example.myapplication.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.view.activity.MenuActivity;

public final class PauseFragment extends DialogFragment {

    public PauseFragment() {
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_pause, null);
        builder.setView(view);

        Button btnMenu = view.findViewById(R.id.menu2);
        btnMenu.setOnClickListener(v -> {
            var intent = new Intent(getContext(), MenuActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });

        Button btnNext = view.findViewById(R.id.btnContinue);
        btnNext.setOnClickListener(v -> {
            builder.cancel();
        });

        return builder;
    }
}

