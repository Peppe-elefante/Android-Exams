package com.example.esameguessword

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.DialogInterface
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    val words = arrayOf("GUESS", "RANDOM", "WORD", "NOW","RAINBOW","WONDER", "MOBILE", "UNISA", "INSTAGRAM",
    "ESAME", "ANDROID")
    lateinit var tvWord : TextView
    lateinit var etGuess : EditText
    lateinit var tvCount : TextView
    lateinit var displayWord : CharArray
    lateinit var randomWord : String
    var countTry = 0
    var isCompleted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        randomWord = words.random()
        displayWord = "_".repeat(randomWord.length).toCharArray()

        tvWord = findViewById(R.id.tvWord)
        etGuess = findViewById(R.id.etGuess)
        tvCount = findViewById(R.id.tvCount)

        updateword()

    }

    fun updateword(){
        if(!isCompleted){
        tvWord.text = displayWord.joinToString(" ")
        tvCount.text = "tentativi = $countTry"
        }
        if(displayWord.joinToString("") == randomWord){
            Toast.makeText(this, "Guessed Correctly", Toast.LENGTH_SHORT).show()
            isCompleted = true
        }
    }

    fun guessLetter(v : View){

        if(etGuess.text.isNotEmpty()){
            val letGuess = (etGuess.text.toString()).uppercase()[0]
            if(letGuess in randomWord && letGuess !in displayWord){
                val indexes : List<Int> = randomWord.mapIndexedNotNull { index, c ->
                    if (c == letGuess) index else null
                }
                for(index in indexes){
                    displayWord[index] = letGuess
                }
            }else{
                countTry++
            }
            updateword()
        }
    }

    fun resetGame(v: View){
        MaterialAlertDialogBuilder(this)
            .setTitle("Reset")
            .setMessage("Are you sure you want to reset")
            .setNegativeButton("No"
            ) { dialog, whichButton ->
                Toast.makeText(this, "Not Reseted", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Yes"
            ) { dialog, whichButton ->
                val oldWord = randomWord
                while(oldWord == randomWord) {
                    randomWord = words.random()
                }
                isCompleted = false
                countTry = 0
                displayWord = "_".repeat(randomWord.length).toCharArray()
                updateword()
            }
            .show()
    }

    fun helpGame(v:View){
        if(isCompleted){
            Toast.makeText(this, "Word is already completed", Toast.LENGTH_SHORT).show()
        } else{
            countTry += 5
            var randomLet = Random.nextInt(randomWord.length)
            while (displayWord[randomLet] != '_'){
                randomLet = Random.nextInt(randomWord.length)
            }
            displayWord[randomLet] = randomWord[randomLet]
            updateword()
        }

    }




}