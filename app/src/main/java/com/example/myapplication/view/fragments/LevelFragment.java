package com.example.myapplication.view.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.serialization.LevelInfoJSON;
import com.example.myapplication.serialization.StageJSON;
import com.example.myapplication.R;
import com.example.myapplication.view.Draw2D;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public final class LevelFragment extends Fragment {
    private static FragmentManager manager;
    private Draw2D canvas;

    public static void showVictoryFragment(int level) {
        var victoryFragment = new VictoryFragment(level);
        victoryFragment.show(manager, "victoryFragment");
    }

    public View getCanvas() {
        return canvas;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        var display = this.requireActivity().getWindowManager().getDefaultDisplay();
        var point = new Point();
        display.getSize(point);
        var width = point.x;
        var height = point.y;

        var nameLevel = requireActivity().getIntent().getStringExtra("level");
        var level = getLevel(nameLevel, width, height);

        var view = inflater.inflate(R.layout.fragment_input, container, false);
        canvas = view.findViewById(R.id.canvas);
        canvas.setLevel(level);

        var btnAddFunction = view.findViewById(R.id.add_function);
        manager = LevelFragment.this.getParentFragmentManager();
        btnAddFunction.setOnClickListener(v -> {
            var inputFragment = new InputFragment(canvas, level);
            inputFragment.show(manager, "inputFragment");
        });

        var btnRun = view.findViewById(R.id.runbutton);
        btnRun.setOnClickListener(v -> {
            var runFragment = new RunFragment(canvas, level.getLevel());
            runFragment.show(manager, "runFragment");
        });

        var btnPause = view.findViewById(R.id.pausebutton);
        btnPause.setOnClickListener(v -> {
            var pauseFragment = new PauseFragment();
            pauseFragment.show(manager, "pauseFragment");
        });

        return view;
    }

    public StageJSON getLevel(String nameJsonFile, float width, float height) {
        StageJSON stageJSON;
        LevelInfoJSON levelInfoJSON;
        try {
            var objectMapper = new ObjectMapper();
            var string = readFileInAssets(nameJsonFile);
            string = string
                    .replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                    .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                    .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                    .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                    .replace("{{INDENT}}", String.valueOf(20));
            levelInfoJSON = objectMapper.readValue(string, LevelInfoJSON.class);
            System.out.println(levelInfoJSON.getLevel() + levelInfoJSON.getCampaignName());
            stageJSON = objectMapper.readValue(string, StageJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stageJSON;
    }

    public String readFileInAssets(String name) {

        byte[] buffer;
        InputStream is;
        try {
            is = this.requireActivity().getAssets().open(name);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(buffer);
    }
}
