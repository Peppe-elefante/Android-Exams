package com.example.esame3java;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity implements UserInputFragment.OnMessageSendListener{

    List<Score> top3 = new ArrayList<>();
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    TextView tvScore;

    boolean isPlay = false;
    int score = 0;

    FragmentManager fm;
    FragmentTransaction ft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvScore = (TextView) findViewById(R.id.tvScore);

        Cursor cursor = dbHelper.getAllScores();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                int score = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_SCORE));
                top3.add(new Score(score, name));
            } while (cursor.moveToNext());
            cursor.close();
            top3.sort(Comparator.comparingInt(Score::getScore).reversed());
        }

        fm = getFragmentManager();
        ft = fm.beginTransaction();
    }

    public void startGame(View v){
        if(!isPlay){
            isPlay = true;
            score = 0;
            tvScore.setText("0");
        }else{
            Toast.makeText(getApplicationContext(), "The game has already started", Toast.LENGTH_LONG).show();
        }
    }

    public void stopGame(View v){
        if(isPlay){
            isPlay = false;
            for(Score s : top3){
                if(score > s.getScore()){

                    ft.replace(R.id.fragmentContainer, new UserInputFragment())
                            .addToBackStack(null)
                            .commit();
                    break;
                }
            }
        } else{
            Toast.makeText(getApplicationContext(), "The game has not started yet", Toast.LENGTH_LONG).show();
        }
    }

    public void incrementScore(View v){
        if(isPlay){
            score++;
            tvScore.setText(String.valueOf(score));
        } else{
            Toast.makeText(getApplicationContext(), "The game has not started yet", Toast.LENGTH_LONG).show();
        }
    }

    public void decrementScore(View v){
        if(isPlay){
            score--;
            tvScore.setText(String.valueOf(score));
        } else{
            Toast.makeText(getApplicationContext(), "The game has not started yet", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onMessageSend(String name) {
        Score lowest = top3.get(2);
        Score player = new Score(score, name);

        // Update the database asynchronously
        new Thread(() -> {
            dbHelper.deleteScore(lowest.getName());
            dbHelper.insertScore(player);
        }).start();

        // Update the top3 list
        top3.remove(lowest);
        top3.add(player);

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }


}
