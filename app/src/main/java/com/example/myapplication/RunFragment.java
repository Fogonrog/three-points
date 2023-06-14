package com.example.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
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
        final var yes = "Да";
        final var no = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Подтверждение")
                .setCancelable(false)
                .setMessage("Вы хотите продолжить?")
                .setPositiveButton(yes, (dialog, id) -> {
                    var result = bigCanvas.isRightFunction();
                    if (result) {
                        ChooseLevelActivity.saveProgress(level);
                        var btnAddFunction = requireActivity().findViewById(R.id.add_function);
                        btnAddFunction.setEnabled(false);

                        var btnRun = requireActivity().findViewById(R.id.runbutton);
                        btnRun.setEnabled(false);

                        var btnPause = requireActivity().findViewById(R.id.pausebutton);
                        btnPause.setEnabled(false);

                        ImageView image = requireActivity().findViewById(R.id.photo);
                        var train = new Train(bigCanvas, image, level);
                        train.startAnimation();
                    } else {
                        Toast.makeText(getActivity(), "Неправильная функция", Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
