package com.example.esame9java;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    int[] btnIds = {R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,
            R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12,
            R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16};

    int[] game = {-1,2,2,1,
                   2,-1,2,-1,
                    1,2,3,2,
                    0,1,-1,1};

    Map<Integer, Integer> map = new HashMap<>();

    boolean perso = false;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < 16; i++){
            map.put(btnIds[i], game[i]);
        }
    }

    public void play(View v){
        if(perso == false) {
            Button btn = (Button) v;
            int valore = map.get(btn.getId());
            if (valore == -1) {
                Toast.makeText(this, "Bomba! Hai perso!", Toast.LENGTH_SHORT).show();
                btn.setText("\uD83D\uDCA3");
                perso = true;
            } else {
                btn.setText(String.valueOf(valore));
            }
        } else{
            Toast.makeText(this, "Hai perso premi RESET per ricominciare", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View v){
        perso = false;
        for(int id : btnIds){
            Button btn = findViewById(id);
            btn.setText("");
        }
    }

    public void mostra(View v){
        for(int id : btnIds){
            Button btn = findViewById(id);
            int valore = map.get(id);
            if(valore == -1){
                btn.setText("\uD83D\uDCA3");
            } else{
                btn.setText(String.valueOf(valore));
            }
        }
    }
}
