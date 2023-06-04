package com.example.myapplication;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public final class LevelFragment extends Fragment {
    private Draw2D canvas;

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
        var manager = LevelFragment.this.getParentFragmentManager();
        btnAddFunction.setOnClickListener(v -> {
            var inputFragment = new InputFragment(canvas, level);
            inputFragment.show(manager, "inputFragment");
        });

        var btnRun = view.findViewById(R.id.runbutton);
        btnRun.setOnClickListener(v -> {
            var runFragment = new RunFragment(canvas, level.getLevel());
            runFragment.show(manager, "runFragment");
        });

        ImageView image = view.findViewById(R.id.photo);
        var train = new Train(canvas, image);

        var btnPause = view.findViewById(R.id.pausebutton);
        btnPause.setOnClickListener(v -> {
            train.startAnimation();
        });

        return view;
    }
    public Level getLevel(String nameJsonFile, float  width, float height){
        Level level;
        try {
            var objectMapper = new ObjectMapper();
            var string = readFileInAssets(nameJsonFile);
            string = string
                    .replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                    .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                    .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                    .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                    .replace("{{INDENT}}", String.valueOf(20));
            level = objectMapper.readValue(string, Level.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return level;
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
