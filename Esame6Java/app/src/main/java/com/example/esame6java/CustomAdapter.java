package com.example.esame6java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Libro> {

    private LayoutInflater inflater;
    private TextView tvTitolo;
    private TextView tvAutore;

    public CustomAdapter(Context context, int resourceId, List<Libro> objects) {
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(v == null){
            v = inflater.inflate(R.layout.book_element, null);
        }

        Libro l = getItem(position);
        tvTitolo = v.findViewById(R.id.tvTitolo);
        tvAutore = v.findViewById(R.id.tvAutore);

        tvTitolo.setText(l.getTitolo());
        tvAutore.setText(l.getAutore());

        return v;
    }
}
