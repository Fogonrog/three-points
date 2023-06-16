package com.example.myapplication.view.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.logic.LevelService;
import com.example.myapplication.logic.model.Level;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.serialization.Parser;
import com.example.myapplication.R;
import com.example.myapplication.view.Draw2D;

import java.util.concurrent.CompletableFuture;

public final class LevelFragment extends Fragment {
    private static FragmentManager manager;
    private Draw2D canvas;
    private AppDatabase db;
    private LevelService levelService;

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

        var parser = new Parser(width, height);
        db = AppDatabase.getDatabase(requireActivity());
        levelService = new LevelService(db, parser);

        var levelId = requireActivity().getIntent().getLongExtra("level", 0);
        var level = getLevel(levelId);

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
            var runFragment = new RunFragment(canvas, level.getInfo().getNumber(), level.getInfo().getCampaignName());
            runFragment.show(manager, "runFragment");
        });

        var btnPause = view.findViewById(R.id.pausebutton);
        btnPause.setOnClickListener(v -> {
            var pauseFragment = new PauseFragment();
            pauseFragment.show(manager, "pauseFragment");
        });

        return view;
    }

    private Level getLevel(long levelId) {
        CompletableFuture<Level> future = new CompletableFuture<>();
        AppDatabase.execute(() -> {
            var level = levelService.getLevelById(levelId);
            future.complete(level);
        });
        return future.join();
    }
}
