package com.example.esame6java;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText etTitolo;
    EditText etAutore;
    ListView lvLibro;

    List<Libro> libri = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitolo = findViewById(R.id.etTitolo);
        etAutore = findViewById(R.id.etAutore);
        lvLibro = findViewById(R.id.lvLibro);

        libri.add(new Libro("The Hobbit", "Tolkien"));
        adapter = new CustomAdapter(this, R.layout.book_element, libri);
        lvLibro.setAdapter(adapter);

        lvLibro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteDialog(position);
            }
        });
    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Item");
        builder.setMessage("Are you sure you want to delete this item?");

        // Set up the "Yes" button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                libri.remove(position);

                adapter.notifyDataSetChanged();


                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });


        builder.create().show();
    }

    public void inserisciLibro(View v){
        String titolo = etTitolo.getText().toString();
        String autore = etAutore.getText().toString();

        if(titolo.strip().equals("") || autore.strip().equals("")){
            Toast.makeText(getApplicationContext(), "Input incompleto", Toast.LENGTH_SHORT).show();
        } else{
            Libro l = new Libro(titolo, autore);
            libri.add(l);
            adapter.notifyDataSetChanged();
        }
    }


}
