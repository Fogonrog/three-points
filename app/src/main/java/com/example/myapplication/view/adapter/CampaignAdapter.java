package com.example.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.logic.model.Campaign;

import java.util.List;

public class CampaignAdapter extends ArrayAdapter<Campaign> {

    public CampaignAdapter(Context context, List<Campaign> list) {
        super(context, R.layout.adapter_item, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Campaign campaign = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item,null);
        }
        ((TextView) convertView.findViewById(R.id.name)).setText(campaign.getName());
        return convertView;
    }
}