package com.example.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.logic.model.Level;

import java.util.List;
import java.util.Locale;

public final class LevelAdapter extends ArrayAdapter<Level> {

    public LevelAdapter(Context context, List<Level> list) {
        super(context, R.layout.adapter_item, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Level level = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }
        ((TextView) convertView
                .findViewById(R.id.name))
                .setText(String.format(Locale.getDefault(),
                        "%d", level.getInfo().getNumber()));
        return convertView;
    }
}
