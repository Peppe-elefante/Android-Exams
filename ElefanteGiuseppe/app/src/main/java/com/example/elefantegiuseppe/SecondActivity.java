package com.example.elefantegiuseppe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class SecondActivity extends Activity {

    DbHelper dbHelper;
    EditText etNome;
    EditText etNumero;
    List<Oggetto> oggetti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        etNome = findViewById(R.id.etNome);
        etNumero = findViewById(R.id.etNumero);

        dbHelper = new DbHelper(this);
        oggetti = dbHelper.getAllOggetti();
    }



    public void ritorna(View v){
        finish();

    }

    //metodo onClick per inserire il valore
    public void inserisci(View v){
        String nome  = String.valueOf(etNome.getText()).toLowerCase().strip();
        String numero = String.valueOf(etNumero.getText()).strip();

        if(nome.equals("") || numero.equals("") || !numero.matches("-?\\d+")){
            Toast.makeText(this, "INVALID INPUT", Toast.LENGTH_SHORT).show();
        } else {
            if(!isPresent(nome, numero)){
                oggetti.add(new Oggetto(nome, Integer.parseInt(numero)));
                Toast.makeText(this, "nuovo oggetto " + nome + " creato", Toast.LENGTH_SHORT).show();
            }
            dbHelper.deleteDatabase();
            dbHelper.insertAllOggetto(oggetti);
            etNumero.setText("");
            etNome.setText("");
        }
    }

    //meoto privato per verificare se l'elemento gia esiste
    private boolean isPresent(String nome, String numero){
        boolean flag = false;
        Iterator<Oggetto> iterator = oggetti.iterator();
        while (iterator.hasNext()){
            Oggetto o = iterator.next();
            if(o.getNome().equals(nome)){
                flag = true;
                int newNum = o.getNumero() + Integer.parseInt(numero);
                if(newNum > 0){
                    o.setNumero(newNum);
                    Toast.makeText(this, "Oggetto " + nome + " aggiornato.", Toast.LENGTH_SHORT).show();
                }
                else{
                    oggetti.remove(o);
                    Toast.makeText(this, "Oggetto " + nome + " rimosso.", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
        return flag;
    }
}
