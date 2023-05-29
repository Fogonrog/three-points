package com.example.myapplication;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.Drawable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public final class MainFragment extends Fragment {

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

        Level level;
        List<Function> functions;
        List<Drawable> requiredObstacles;
        List<Drawable> forbiddenObstacles;
        List<Drawable> environment;

        try {
            var objectMapper = new ObjectMapper();
            var string = readFileInAssets("level-1.json");
            string = string
                    .replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                    .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                    .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                    .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                    .replace("{{INDENT}}", String.valueOf(20));
            level = objectMapper.readValue(string, Level.class);
            functions = level.getFunctions();
            requiredObstacles = level.getRequiredObstacles();
            forbiddenObstacles = level.getForbiddenObstacles();
            environment = level.getEnvironment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var view = inflater.inflate(R.layout.fragment_input, container, false);
        canvas = view.findViewById(R.id.canvas);
        canvas.setEnvironment(environment);
        canvas.setRequiredObstacles(requiredObstacles);
        canvas.setForbiddenObstacles(forbiddenObstacles);

        var btnAddFunction = view.findViewById(R.id.add_function);
        var manager = MainFragment.this.getParentFragmentManager();
        btnAddFunction.setOnClickListener(v -> {
            var inputFragment = new InputFragment(canvas,
                    functions,
                    environment,
                    requiredObstacles,
                    forbiddenObstacles);
            inputFragment.show(manager, "inputFragment");
        });

        var btnRun = view.findViewById(R.id.runbutton);
        btnRun.setOnClickListener(v -> {
            var runFragment = new RunFragment(canvas);
            runFragment.show(manager, "runFragment");
        });

        return view;
    }

    public String readFileInAssets(String name) {

        byte[] buffer = null;
        InputStream is;
        try {
            is = this.requireActivity().getAssets().open(name);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(buffer);
    }
}
