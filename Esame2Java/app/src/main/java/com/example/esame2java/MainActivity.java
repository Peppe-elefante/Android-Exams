package com.example.esame2java;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText etEnterName;
    private EditText etEnterNumber;
    private EditText etEnterEmail;
    private ListView lvContacts;
    private CustomAdapter customAdapter;
    private List<Contatto> myContactList = new ArrayList<Contatto>();

    private DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEnterName = (EditText) findViewById(R.id.etEnterName);
        etEnterNumber = (EditText) findViewById(R.id.etEnterNumber);
        etEnterEmail = (EditText) findViewById(R.id.etEnterEmail);
        lvContacts = (ListView) findViewById(R.id.lvContacts);


        myContactList = dbHelper.getAllContacts();
        customAdapter = new CustomAdapter(this, R.layout.list_element, myContactList);
        lvContacts.setAdapter(customAdapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contatto c  = (Contatto) lvContacts.getItemAtPosition(position);
                // Remove the item from the data source (e.g., the list)
                myContactList.remove(position);
                dbHelper.deleteDatabase();
                dbHelper.insertAllContatto(myContactList);
                // Notify the adapter that the data has changed
                customAdapter.notifyDataSetChanged();

            }
        });
    }

    public void inserisci(View v){
        String number = String.valueOf(etEnterNumber.getText());
        String name = String.valueOf(etEnterName.getText());
        String email = String.valueOf(etEnterEmail.getText());
        Contatto c = new Contatto(name, number, email);
        myContactList.add(c);
        dbHelper.deleteDatabase();
        dbHelper.insertAllContatto(myContactList);
        customAdapter.add(c);

    }







}
