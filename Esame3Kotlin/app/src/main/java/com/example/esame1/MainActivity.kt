package com.example.esame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnInc: Button
    private lateinit var btnDec: Button
    private lateinit var tvScore: TextView

    private var isStarted = false
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnInc = findViewById(R.id.btnInc)
        btnDec = findViewById(R.id.btnDec)
        tvScore = findViewById(R.id.tvScore)

        btnStart.setOnClickListener{
            isStarted = true
        }
        btnStop.setOnClickListener{
            if(isStarted) {
                isStarted = false
                updateScore()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("curScore", score)
                startActivity(intent)
            }
        }
        btnInc.setOnClickListener{
            if(isStarted){
                score++
                updateScore()
            }
        }
        btnDec.setOnClickListener{
            if(isStarted){
                score--
                updateScore()
            }
        }


    }

    fun updateScore(){
        tvScore.text = score.toString()
    }
}