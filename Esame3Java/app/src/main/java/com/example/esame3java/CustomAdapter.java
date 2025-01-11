package com.example.esame3java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter  extends ArrayAdapter<Score> {
    private LayoutInflater inflater;
    private TextView tvName;
    private TextView tvScore;

    public CustomAdapter(Context context, int resourceId, List<Score> objects) {
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.score_element, null);
        }
        Score s = getItem(position);

        tvName = v.findViewById(R.id.tvName);
        tvScore = v.findViewById(R.id.tvScore);

        tvName.setText(s.getName());
        tvScore.setText(String.valueOf(s.getScore()));

        return v;
    }
}
