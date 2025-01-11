package com.example.esame3java;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SecondActivity extends Activity {

    ListView lvLeaderboard;
    CustomAdapter adapter;

    List<Score> top3 = new ArrayList<>();
    DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

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

        lvLeaderboard = findViewById(R.id.lvLeaderboard);
        adapter = new CustomAdapter(this, R.layout.score_element, top3);
        lvLeaderboard.setAdapter(adapter);
    }
}
