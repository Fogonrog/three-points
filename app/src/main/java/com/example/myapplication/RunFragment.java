package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public final class RunFragment extends DialogFragment {

    private final Draw2D bigCanvas;

    public RunFragment(Draw2D bigCanvas) {
        super();
        this.bigCanvas = bigCanvas;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String yes = "Да";
        String no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение")
                .setMessage("Вы хотите продолжить?")
                .setPositiveButton(yes, (dialog, id) -> {
                    Toast.makeText(getActivity(), "" + bigCanvas.isRightFunction(), Toast.LENGTH_LONG).show();
                    dialog.cancel();
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
