package com.example.myapplication.view.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.view.Train;
import com.example.myapplication.view.Draw2D;
import com.example.myapplication.view.activity.ChooseLevelActivity;


public final class RunFragment extends DialogFragment {

    private final Draw2D bigCanvas;
    private int level;
    private final String campaignName;

    public RunFragment(Draw2D bigCanvas, int level, String campaignName) {
        super();
        this.bigCanvas = bigCanvas;
        this.level = level;
        this.campaignName = campaignName;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final var yes = getString(R.string.yes);
        final var no = getString(R.string.no);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.confirmation))
                .setCancelable(false)
                .setMessage(getString(R.string.wantToContinue))
                .setPositiveButton(yes, (dialog, id) -> {
                    var result = bigCanvas.isRightFunction();
                    if (result) {
                        ChooseLevelActivity.saveProgress(campaignName, level);
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
                        Toast.makeText(getActivity(), getString(R.string.wrongFunction), Toast.LENGTH_SHORT).show();
                    }
                    dialog.cancel();
                })
                .setNegativeButton(no, (dialog, id) -> dialog.cancel())
                .setCancelable(true);

        return builder.create();
    }
}
