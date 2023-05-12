package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public final class RunFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String yes = "Да";
        String no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение");
        builder.setMessage("Вы хотите продолжить?");
        builder.setPositiveButton(yes, (dialog, id) -> {
            Toast.makeText(getActivity(), "" + Draw2D.getCorrectFunction(), Toast.LENGTH_LONG).show();
            dialog.cancel();
        });
        builder.setNegativeButton(no, (dialog, id) -> dialog.cancel());
        builder.setCancelable(true);

        return builder.create();
    }
}
