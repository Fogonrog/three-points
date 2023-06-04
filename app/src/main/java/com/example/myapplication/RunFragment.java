package com.example.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;


public final class RunFragment extends DialogFragment {

    private final Draw2D bigCanvas;
    private final int level;

    public RunFragment(Draw2D bigCanvas, int level) {
        super();
        this.bigCanvas = bigCanvas;
        this.level = level;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        var yes = "Да";
        var no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение")
                .setMessage("Вы хотите продолжить?")
                .setPositiveButton(yes, (dialog, id) -> {
                    var result = bigCanvas.isRightFunction();
                    Toast.makeText(getActivity(), "" + result, Toast.LENGTH_SHORT).show();
                    if (result) {
                        ChooseLevelActivity.saveProgress(level);
                        var intent = new Intent(getContext(), ChooseLevelActivity.class);
                        startActivity(intent);
                    }
                    dialog.cancel();
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
