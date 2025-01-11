package com.example.esame7java;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends Activity {

    Map<Integer, Integer> map = new HashMap<>();
    int numTrovato = 0;
    int score = 0;

    int[] btnIds = {R.id.btn1, R.id.btn2,R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6,R.id.btn7, R.id.btn8,
            R.id.btn9, R.id.btn10,R.id.btn11, R.id.btn12,
            R.id.btn13, R.id.btn14,R.id.btn15, R.id.btn16};

    int[] valori = new int[16];
    private static Random random;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 1; i < 9; i++){
            valori[i-1] = i;
            valori[16-i] = i;
        }

        realMischia();

        for (int i = 0; i < 16; i++) {
            map.put(btnIds[i], valori[i]);
        }
        Log.d("onCreate","App created!");
    }

    public void play(View v) throws InterruptedException {
        Log.d("onClick","The play button works");
        Button btn = (Button) v;
        Integer value = map.getOrDefault(btn.getId(), 0);
        btn.setText(String.valueOf(value));
       if (numTrovato == 0){
           numTrovato = value;
       }else if(numTrovato == value){
           score++;
           numTrovato = 0;
           if(score == 8) playerVictory();
       } else{
           Toast.makeText(this, "The number is "+ value, Toast.LENGTH_SHORT).show();
           clearBoard();
       }

    }

    private void clearBoard(){
        for(int id : btnIds){
            Button btn = findViewById(id);
            btn.setText("▢");
            score = 0;
            numTrovato = 0;
        }
    }

    private void playerVictory() throws InterruptedException {
        Toast.makeText(this, "Congratulations you won!", Toast.LENGTH_SHORT).show();
        Thread.sleep(100);
        clearBoard();
        realMischia();
    }

    public void resetGame(View v){
        clearBoard();
    }

    private void realMischia(){

        List<Integer> list = new ArrayList<>();
        for (int val : valori) {
            list.add(val);
        }

        Collections.shuffle(list);

        for (int i = 0; i < valori.length; i++) {
            valori[i] = list.get(i);
        }
    }

    /** Il codice del prof non funziona :(

     private void mischia() {
        int next = 1;
        for (int i=0; i<8; i++) {
            valori[2*i]=next;
            valori[2*i+1]=next;
            next++;
            shuffle(valori);
        }
    }

    private void shuffle(int[] array) {
        if (random == null) random = new
                Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }*/
}
