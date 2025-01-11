package com.example.esame1

import android.os.Bundle
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter


class SecondActivity: AppCompatActivity() {

    lateinit var btnInsert: Button
    lateinit var etName: EditText
    lateinit var dbHelper: DatabaseHelper
    lateinit var scores: MutableList<Score>
    lateinit var lvScoreBoard : ListView
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        dbHelper = DatabaseHelper(this)
        btnInsert = findViewById(R.id.btnInsert)
        etName = findViewById(R.id.etName)
        lvScoreBoard = findViewById(R.id.lvScoreboard)
        val curScore : Int = intent.getIntExtra("curScore", 0)
        uploadSB()

         btnInsert.setOnClickListener{
             dbHelper.addScore((etName.text).toString(), curScore )
             uploadSB()
         }


    }

    fun uploadSB(){
        scores = dbHelper.getAllScores().toMutableList()
        scores = scores.sortedByDescending { it.score } as MutableList<Score>
        val adapter = ScoreAdapter(this, scores.take(3))
        lvScoreBoard.adapter = adapter
    }
}