package com.example.myapplication.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public final class ExitFragment extends DialogFragment {
    public ExitFragment() {
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final var yes = "Да";
        final var no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение")
                .setMessage("Вы точно хотите выйти?")
                .setPositiveButton(yes, (dialog, id) -> {
                    dialog.cancel();
                    requireActivity().finish();
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
