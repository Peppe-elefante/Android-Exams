package com.example.elefantegiuseppe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    ListView lvMagazzino;
    List<Oggetto> oggetti = new ArrayList<>();
    CustomAdapter adapter;
    DbHelper dbHelper;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMagazzino = findViewById(R.id.lvMagazzino);

        /**dbHelper = new DbHelper(this);
        oggetti = dbHelper.getAllOggetti();
        adapter = new CustomAdapter(this, R.layout.list_element, oggetti);
        lvMagazzino.setAdapter(adapter);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = new DbHelper(this);
        oggetti = dbHelper.getAllOggetti();
        adapter = new CustomAdapter(this, R.layout.list_element, oggetti);
        lvMagazzino.setAdapter(adapter);
    }

    public void inserisci(View v){
        Intent i = new Intent(this, SecondActivity.class );
        startActivity(i);
    }
}
