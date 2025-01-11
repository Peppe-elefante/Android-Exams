package com.example.esame1java;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {

    int[] nums = {1,2,3,4,5,6,7,8,9};

    int[] tvIds ={R.id.tvGrid1, R.id.tvGrid2, R.id.tvGrid3,
            R.id.tvGrid4, R.id.tvGrid5, R.id.tvGrid6,
            R.id.tvGrid7, R.id.tvGrid8, R.id.tvGrid9};

    int score;
    TextView tvScore;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realMischia();
        impostaNumeri();
        tvScore = findViewById(R.id.tvScore);
        tvScore.setText(String.valueOf(score));
    }

    public void playGioco(View v) throws InterruptedException {
        score++;
        tvScore.setText(String.valueOf(score));

        if (v.getId() == R.id.btn1) {
            shiftUp(1);
        } else if (v.getId() == R.id.btn2) {
            shiftUp(2);
        } else if (v.getId() == R.id.btn3) {
            shiftUp(3);
        } else if (v.getId() == R.id.btn4) {
            shiftLeft(3);
        } else if (v.getId() == R.id.btn5) {
            shiftLeft(6);
        } else if (v.getId() == R.id.btn6) {
            shiftLeft(9);
        } else if (v.getId() == R.id.btn7) {
            shiftDown(9);
        } else if (v.getId() == R.id.btn8) {
            shiftDown(8);
        } else if (v.getId() == R.id.btn9) {
            shiftDown(7);
        } else if (v.getId() == R.id.btn10) {
            shiftRight(7);
        } else if (v.getId() == R.id.btn11) {
            shiftRight(4);
        } else if (v.getId() == R.id.btn12) {
            shiftRight(1);
        }
        impostaNumeri();
        checkVittoria();
    }

    private void impostaNumeri(){
        int counter = 0;
        for(int id : tvIds){
            TextView tv = findViewById(id);
            tv.setText(String.valueOf(nums[counter++]));
        }
    }

    private void reset(){
        score = 0;
        tvScore.setText("0");
        realMischia();
        impostaNumeri();
    }

    public void resetGame(View v){
        reset();
    }

    private void checkVittoria() throws InterruptedException {
        for(int i = 0; i < 9; i++){
            if(nums[i] != i+1) return;
        }
        Toast.makeText(this, "Hai vinto il gioco in " + score + " mosse", Toast.LENGTH_LONG).show();
        Thread.sleep(2000);
        reset();
    }

    private void shiftLeft(int n){
        int temp = nums[n-1];
        nums[n-1] = nums[n-2];
        nums[n-2] = nums[n-3];
        nums[n-3] = temp;
    }

    private void shiftDown(int n){
        int temp = nums[n-1];
        nums[n-1] = nums[n-4];
        nums[n-4] = nums[n-7];
        nums[n-7] = temp;
    }

    private void shiftUp(int n){
        int temp = nums[n-1];
        nums[n-1] = nums[n+2];
        nums[n+2] = nums[n+5];
        nums[n+5] = temp;
    }

    private void shiftRight(int n){
        int temp = nums[n-1];
        nums[n-1] = nums[n];
        nums[n] = nums[n+1];
        nums[n+1] = temp;
    }
    private void realMischia(){
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.shuffle(list);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }
}
