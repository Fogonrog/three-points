package com.example.myapplication.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.view.activities.ChooseCampaignActivity;
import com.example.myapplication.view.activities.MenuActivity;

public final class VictoryFragment extends DialogFragment {

    private final int level;

    public VictoryFragment(int level) {
        super();
        this.level = level;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_victory, null);
        builder.setView(view);

        var btnAddFunction = requireActivity().findViewById(R.id.add_function);
        btnAddFunction.setEnabled(true);

        var btnRun = requireActivity().findViewById(R.id.runbutton);
        btnRun.setEnabled(true);

        var btnPause = requireActivity().findViewById(R.id.pausebutton);
        btnPause.setEnabled(true);

        TextView twLevel = view.findViewById(R.id.level);
        twLevel.setText("Уровень " + level);

        Button btnMenu = view.findViewById(R.id.menu);
        btnMenu.setOnClickListener(v -> {
            var intent = new Intent(getContext(), MenuActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });

        Button btnNext = view.findViewById(R.id.next);
        btnNext.setOnClickListener(v -> {
            var intent = new Intent(getContext(), ChooseCampaignActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });
        return builder;
    }
}

