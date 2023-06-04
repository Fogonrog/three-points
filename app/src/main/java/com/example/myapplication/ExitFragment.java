package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
        var yes = "Да";
        var no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение")
                .setMessage("Вы точно хотите выйти?")
                .setPositiveButton(yes, (dialog, id) -> {
                    dialog.cancel();
                    Intent intent = new Intent(requireActivity(), MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    requireActivity().finishAffinity();
                    System.exit(0);
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
