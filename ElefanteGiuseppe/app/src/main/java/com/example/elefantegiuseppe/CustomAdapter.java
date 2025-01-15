package com.example.elefantegiuseppe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Oggetto> {

    private LayoutInflater inflater;
    private TextView tvNome;
    private TextView tvNumero;

    public CustomAdapter(Context context, int resourceId, List<Oggetto> objects) {
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if(v == null){
            v = inflater.inflate(R.layout.list_element, null);
        }

        Oggetto o = getItem(position);
        tvNome = v.findViewById(R.id.tvNome);
        tvNumero = v.findViewById(R.id.tvNumero);

        tvNome.setText(o.getNome());
        tvNumero.setText(String.valueOf(o.getNumero()));

        return v;
    }
}
