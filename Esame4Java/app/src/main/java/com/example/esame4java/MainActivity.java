package com.example.esame4java;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    String parola = "CIAO";

    TextView tvParola;
    EditText etLettera;
    TextView tvTentativi;

    int tentativi = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvParola = findViewById(R.id.tvParola);
        etLettera = findViewById(R.id.etLettera);
        tvTentativi = findViewById(R.id.tvTentativi);

        if(savedInstanceState != null){
            tvParola.setText(savedInstanceState.getString("tvParola"));
            tentativi = savedInstanceState.getInt("tentativi");
            tvTentativi.setText("Tentativi: "+ tentativi);
        }
    }

    public void indovinaLettera(View v){
        String lettera = String.valueOf(etLettera.getText().charAt(0)).toUpperCase();
        String indovinato = String.valueOf(tvParola.getText());
        if(parola.contains(lettera)){
            if(indovinato.contains(lettera)){
                Toast.makeText(getApplicationContext(), "Lettera già indovinata", Toast.LENGTH_SHORT).show();
            } else{
                int index = parola.indexOf(lettera);
                String newStr = indovinato.substring(0, index) + lettera + indovinato.substring(index + 1);
                tvParola.setText(newStr);
                if(newStr.equals(parola)){
                    Toast.makeText(getApplicationContext(), "Complimenti hai indovinato!", Toast.LENGTH_SHORT).show();
                    riavviaGioco(v);
                }
            }
        } else{
            Toast.makeText(getApplicationContext(), "Lettera sbagliata", Toast.LENGTH_SHORT).show();
            tentativi++;
            tvTentativi.setText("Tentativi: " + String.valueOf(tentativi));
        }
    }

    public void riavviaGioco(View v){
            tentativi = 0;
            tvTentativi.setText("Tentativi: 0");
            tvParola.setText("____");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save data into the outState bundle
        outState.putString("tvParola", tvParola.getText().toString());
        outState.putInt("tentativi", tentativi);
    }
}
